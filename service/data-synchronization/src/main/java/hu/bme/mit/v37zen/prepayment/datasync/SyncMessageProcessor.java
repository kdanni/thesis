package hu.bme.mit.v37zen.prepayment.datasync;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AccountProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.AssociationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.ContactProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.MeterProcessorConfirugarator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.SdpProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.ServiceLocationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.nodemappers.SyncMessageMapper;
import hu.bme.mit.v37zen.prepayment.datasync.nodemappers.SyncMessageMapper.SyncData;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountContactAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountSDPAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ContactRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.MeterAssetRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpMeterAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpServiceLocationAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceLocationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.w3c.dom.Node;

/**
 * 
 * @author Kiss Dániel
 *
 */
public class SyncMessageProcessor implements Runnable, ApplicationContextAware {

	public static Logger logger = LoggerFactory
			.getLogger(SyncMessageProcessor.class);

	private ApplicationContext applicationContext;

	private Node xmlNode;

	private NamespaceHandler namespaces;

	private AccountProcessorConfigurator accountProcessorConfigurator;
	private SdpProcessorConfigurator sdpProcessorConfigurator;
	private AssociationProcessorConfigurator associationProcessorConfigurator;
	private MeterProcessorConfirugarator meterProcessorConfirugarator;
	private ContactProcessorConfigurator contactProcessorConfigurator;
	private ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator;

	public SyncMessageProcessor(
			NamespaceHandler namespaces,
			AccountProcessorConfigurator accountProcessorConfigurator,
			SdpProcessorConfigurator sdpProcessorConfigurator,
			AssociationProcessorConfigurator associationProcessorConfigurator,
			MeterProcessorConfirugarator meterProcessorConfirugarator,
			ContactProcessorConfigurator contactProcessorConfigurator,
			ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator) {
		super();
		this.namespaces = namespaces;
		this.accountProcessorConfigurator = accountProcessorConfigurator;
		this.sdpProcessorConfigurator = sdpProcessorConfigurator;
		this.associationProcessorConfigurator = associationProcessorConfigurator;
		this.meterProcessorConfirugarator = meterProcessorConfirugarator;
		this.contactProcessorConfigurator = contactProcessorConfigurator;
		this.serviceLocationProcessorConfigurator = serviceLocationProcessorConfigurator;

	}

	public void run() {
		if(this.xmlNode == null){
			logger.warn("Sync message is null!"); 
			return;
		}
		try{
			logger.info("Sync message processing has started.");
			
			SyncData sd = new SyncMessageMapper(namespaces, accountProcessorConfigurator, sdpProcessorConfigurator, 
					associationProcessorConfigurator, meterProcessorConfirugarator, contactProcessorConfigurator, 
					serviceLocationProcessorConfigurator).mapSyncMessage(xmlNode);		
			
			AccountRepository accountRepository =
			applicationContext.getBean(AccountRepository.class);
			accountRepository.save(sd.getAccounts());
			
			ServiceDeliveryPointRepository sdpRepo =
			applicationContext.getBean(ServiceDeliveryPointRepository.class);
			sdpRepo.save(sd.getServiceDeliveryPoints());
			
			AccountSDPAssociationRepository accSdpAssRepo =
			applicationContext.getBean(AccountSDPAssociationRepository.class);
			accSdpAssRepo.save(sd.getAccountSDPAssociations());
			
			MeterAssetRepository meterRepo =
			applicationContext.getBean(MeterAssetRepository.class);
			meterRepo.save(sd.getMeterAssets());
			
			SdpMeterAssociationRepository sdpMeterAssRepo =
			applicationContext.getBean(SdpMeterAssociationRepository.class);
			sdpMeterAssRepo.save(sd.getSdpMeterAssociations());
			
			ContactRepository contactRepo =
			applicationContext.getBean(ContactRepository.class);
			contactRepo.save(sd.getContacts());
			
			ServiceLocationRepository slRepo =
			applicationContext.getBean(ServiceLocationRepository.class);
			slRepo.save(sd.getServiceLocations());
			
			SdpServiceLocationAssociationRepository sslaRepo =
			applicationContext.getBean(SdpServiceLocationAssociationRepository.class);
			sslaRepo.save(sd.getSdpServiceLocationAssociations());
			
			AccountContactAssociationRepository acaRepo =
			applicationContext.getBean(AccountContactAssociationRepository.class);
			acaRepo.save(sd.getAccountContactAssociations());
			
			
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
}