package hu.bme.mit.v37zen.prepayment.datasync;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AccountProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.AssociationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.ContactProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.MeterProcessorConfirugarator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.RouteProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.SdpProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.ServiceLocationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.nodemappers.SyncMessageMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountContactAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountSDPAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ContactRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.MeterAssetRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.RouteRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpMeterAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpRouteAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpServiceLocationAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceLocationRepository;
import hu.bme.mit.v37zen.sm.messaging.SeedData;
import hu.bme.mit.v37zen.sm.messaging.impl.BasicDataProcessRequest;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.w3c.dom.Node;

/**
 * 
 * @author Kiss Dániel
 *
 */
public class SyncMessageProcessorImpl implements SyncMessageProcessor {

	public static Logger logger = LoggerFactory
			.getLogger(SyncMessageProcessorImpl.class);

	private ApplicationContext applicationContext;

	private Node xmlNode;

	private NamespaceHandler namespaces;

	private AccountProcessorConfigurator accountProcessorConfigurator;
	private SdpProcessorConfigurator sdpProcessorConfigurator;
	private AssociationProcessorConfigurator associationProcessorConfigurator;
	private MeterProcessorConfirugarator meterProcessorConfirugarator;
	private ContactProcessorConfigurator contactProcessorConfigurator;
	private ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator;
	private RouteProcessorConfigurator routeProcessorConfigurator;

	public SyncMessageProcessorImpl(
			NamespaceHandler namespaces,
			AccountProcessorConfigurator accountProcessorConfigurator,
			SdpProcessorConfigurator sdpProcessorConfigurator,
			AssociationProcessorConfigurator associationProcessorConfigurator,
			MeterProcessorConfirugarator meterProcessorConfirugarator,
			ContactProcessorConfigurator contactProcessorConfigurator,
			ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator,
			RouteProcessorConfigurator routeProcessorConfigurator) {
		super();
		this.namespaces = namespaces;
		this.accountProcessorConfigurator = accountProcessorConfigurator;
		this.sdpProcessorConfigurator = sdpProcessorConfigurator;
		this.associationProcessorConfigurator = associationProcessorConfigurator;
		this.meterProcessorConfirugarator = meterProcessorConfirugarator;
		this.contactProcessorConfigurator = contactProcessorConfigurator;
		this.serviceLocationProcessorConfigurator = serviceLocationProcessorConfigurator;
		this.routeProcessorConfigurator = routeProcessorConfigurator;
	}

	public void run() {
		if (this.xmlNode == null) {
			logger.warn("Sync message is null!");
			return;
		}
		try {
			logger.info("Sync message processing has started.");

			final SeedData sd = new SyncMessageMapper(namespaces,
					accountProcessorConfigurator, sdpProcessorConfigurator,
					associationProcessorConfigurator,
					meterProcessorConfirugarator, contactProcessorConfigurator,
					serviceLocationProcessorConfigurator,
					routeProcessorConfigurator).mapSyncMessage(xmlNode);

			AccountRepository accountRepository = applicationContext
					.getBean(AccountRepository.class);
			accountRepository.save(sd.getAccounts());

			ServiceDeliveryPointRepository sdpRepo = applicationContext
					.getBean(ServiceDeliveryPointRepository.class);
			sdpRepo.save(sd.getServiceDeliveryPoints());

			AccountSDPAssociationRepository accSdpAssRepo = applicationContext
					.getBean(AccountSDPAssociationRepository.class);
			accSdpAssRepo.save(sd.getAccountSDPAssociations());

			MeterAssetRepository meterRepo = applicationContext
					.getBean(MeterAssetRepository.class);
			meterRepo.save(sd.getMeterAssets());

			SdpMeterAssociationRepository sdpMeterAssRepo = applicationContext
					.getBean(SdpMeterAssociationRepository.class);
			sdpMeterAssRepo.save(sd.getSdpMeterAssociations());

			ContactRepository contactRepo = applicationContext
					.getBean(ContactRepository.class);
			contactRepo.save(sd.getContacts());

			RouteRepository routRepo = applicationContext
					.getBean(RouteRepository.class);
			routRepo.save(sd.getRoutes());

			ServiceLocationRepository slRepo = applicationContext
					.getBean(ServiceLocationRepository.class);
			slRepo.save(sd.getServiceLocations());

			SdpServiceLocationAssociationRepository sslaRepo = applicationContext
					.getBean(SdpServiceLocationAssociationRepository.class);
			sslaRepo.save(sd.getSdpServiceLocationAssociations());

			AccountContactAssociationRepository acaRepo = applicationContext
					.getBean(AccountContactAssociationRepository.class);
			acaRepo.save(sd.getAccountContactAssociations());

			SdpRouteAssociationRepository sraRepo = applicationContext
					.getBean(SdpRouteAssociationRepository.class);
			sraRepo.save(sd.getSdpRouteAssociations());

			JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
			jmsTemplate.send(new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {					
					return session.createObjectMessage(new BasicDataProcessRequest<SeedData>(sd));
				}
			});
			
			
			logger.info("Sync message processing has finished.");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.info("Sync message processing has failed.");
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}

	public Node getXmlNode() {
		return xmlNode;
	}

	public void setXmlNode(Node xmlNode) {
		this.xmlNode = xmlNode;
	}

	public NamespaceHandler getNamespaces() {
		return namespaces;
	}

	public void setNamespaces(NamespaceHandler namespaces) {
		this.namespaces = namespaces;
	}

	public AccountProcessorConfigurator getAccountProcessorConfigurator() {
		return accountProcessorConfigurator;
	}

	public void setAccountProcessorConfigurator(
			AccountProcessorConfigurator accountProcessorConfigurator) {
		this.accountProcessorConfigurator = accountProcessorConfigurator;
	}

	public SdpProcessorConfigurator getSdpProcessorConfigurator() {
		return sdpProcessorConfigurator;
	}

	public void setSdpProcessorConfigurator(
			SdpProcessorConfigurator sdpProcessorConfigurator) {
		this.sdpProcessorConfigurator = sdpProcessorConfigurator;
	}

	public AssociationProcessorConfigurator getAssociationProcessorConfigurator() {
		return associationProcessorConfigurator;
	}

	public void setAssociationProcessorConfigurator(
			AssociationProcessorConfigurator associationProcessorConfigurator) {
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}

	public MeterProcessorConfirugarator getMeterProcessorConfirugarator() {
		return meterProcessorConfirugarator;
	}

	public void setMeterProcessorConfirugarator(
			MeterProcessorConfirugarator meterProcessorConfirugarator) {
		this.meterProcessorConfirugarator = meterProcessorConfirugarator;
	}

	public ContactProcessorConfigurator getContactProcessorConfigurator() {
		return contactProcessorConfigurator;
	}

	public void setContactProcessorConfigurator(
			ContactProcessorConfigurator contactProcessorConfigurator) {
		this.contactProcessorConfigurator = contactProcessorConfigurator;
	}

	public ServiceLocationProcessorConfigurator getServiceLocationProcessorConfigurator() {
		return serviceLocationProcessorConfigurator;
	}

	public void setServiceLocationProcessorConfigurator(
			ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator) {
		this.serviceLocationProcessorConfigurator = serviceLocationProcessorConfigurator;
	}

	public RouteProcessorConfigurator getRouteProcessorConfigurator() {
		return routeProcessorConfigurator;
	}

	public void setRouteProcessorConfigurator(
			RouteProcessorConfigurator routeProcessorConfigurator) {
		this.routeProcessorConfigurator = routeProcessorConfigurator;
	}
}