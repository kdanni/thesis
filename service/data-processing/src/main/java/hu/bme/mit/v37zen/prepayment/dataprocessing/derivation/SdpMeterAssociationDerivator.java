package hu.bme.mit.v37zen.prepayment.dataprocessing.derivation;

import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.jpa.repositories.MeterAssetRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpMeterAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;

public class SdpMeterAssociationDerivator implements MessageHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(SdpMeterAssociationDerivator.class);
	
	private final static String PROCESSED_KEY = "proccessed";
	
	private final static int REPROCESSING_THRESHOLD = 120;
	
	private SubscribableChannel channel;
	
	private MessageChannel rederivatorChannel;
	
	private String inactiveStatus;
	
	private String activeStatus;
	
	@Autowired
	private SdpMeterAssociationRepository sdpMeterAssociationRepository;
	
	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	@Autowired
	private ServiceDeliveryPointRepository serviceDeliveryPointRepository;
	
	@Autowired
	private MeterAssetRepository meterAssetRepository;
	
	@Override
	@Transactional
	public void handleMessage(Message<?> message) throws MessagingException {
		int processed = 0;
		if(message.getHeaders().containsKey(PROCESSED_KEY)){
			processed = message.getHeaders().get(PROCESSED_KEY, Integer.class);
		}
		if(processed > REPROCESSING_THRESHOLD){
			logger.error("Rederivating threshold reached!");
			PrepaymentException pe = new PrepaymentException(new Date(), "Rederivating threshold reached!");
			prepaymentExceptionRepository.save(pe);
			return;
		}
		
		Object payload = message.getPayload();
		if(payload instanceof SdpMeterAssociation){
			logger.debug(payload.toString());

			SdpMeterAssociation sma = (SdpMeterAssociation)payload;
						
			String sdpMRID = sma.getSdpMRID();
			if(sdpMRID == null || sdpMRID.trim().isEmpty()){
				sdpMRID = sma.getServiceDeliveryPoint() != null ? sma.getServiceDeliveryPoint().getMRID() : null;
				sma.setSdpMRID(sdpMRID);
			}
			
			String meterMRID = sma.getMeterAssetMRID();
			if(meterMRID == null || meterMRID.trim().isEmpty()){
				meterMRID = sma.getMeterAsset() != null ? sma.getMeterAsset().getMRID() : null;
				sma.setMeterAssetMRID(meterMRID);
			}
			
			List<ServiceDeliveryPoint> sdpList = getServiceDeliveryPointRepository().findByMRID(sdpMRID); 
			if(sdpList.size() == 0){
				Message<SdpMeterAssociation> m = new GenericMessage<SdpMeterAssociation>(sma);
				m.getHeaders().put(PROCESSED_KEY, new Integer(processed+1));
				logger.debug("Rederivating: " + m.toString());
				this.rederivatorChannel.send(m);
				return;
			} else if (sdpList.size() > 1){
				String msg = "Multiple SDP found with the same id!";
				logger.error(msg);
				PrepaymentException pe = new PrepaymentException(new Date(), msg);
				getPrepaymentExceptionRepository().save(pe);
				return;
			}			
			
			List<MeterAsset> meterList = meterAssetRepository.findByMRID(meterMRID); 
			if(meterList.size() == 0){
				Message<SdpMeterAssociation> m = new GenericMessage<SdpMeterAssociation>(sma);
				m.getHeaders().put(PROCESSED_KEY, new Integer(processed+1));
				logger.debug("Rederivating: " + m.toString());
				this.rederivatorChannel.send(m);
				return;
			} else if (meterList.size() > 1){
				String msg = "Multiple MeterAsset found with the same id!";
				logger.error(msg);
				PrepaymentException pe = new PrepaymentException(new Date(), msg);
				getPrepaymentExceptionRepository().save(pe);
				return;
			}
			
			//Lezárni az aktiv kapcsolatot a megeggyző kapcsolatra.
			List<SdpMeterAssociation> list = sdpMeterAssociationRepository.findBySdpMRID(sdpMRID);
			for (SdpMeterAssociation sdpMeterAssociation : list) {
				if(sdpMeterAssociation.getMeterAssetMRID() != null && sdpMeterAssociation.getMeterAssetMRID().equals(meterMRID)){
					sdpMeterAssociation.setEndDate(new Date());
					sdpMeterAssociation.setStatus(inactiveStatus);
				}
			}	
			
			ServiceDeliveryPoint sdp = sdpList.get(0);
			MeterAsset meter = meterList.get(0);
			
			sma.setServiceDeliveryPoint(sdp);
			sma.setMeterAsset(meter);
			
			sma = sdpMeterAssociationRepository.save(sma);
		}
	}

	public SubscribableChannel getChannel() {
		return channel;
	}

	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		this.channel.subscribe(this);
	}

	public MessageChannel getRederivatorChannel() {
		return rederivatorChannel;
	}

	public void setRederivatorChannel(MessageChannel rederivatorChannel) {
		this.rederivatorChannel = rederivatorChannel;
	}

	public MeterAssetRepository getMeterAssetRepository() {
		return meterAssetRepository;
	}

	public void setMeterAssetRepository(MeterAssetRepository meterAssetRepository) {
		this.meterAssetRepository = meterAssetRepository;
	}

	public ServiceDeliveryPointRepository getServiceDeliveryPointRepository() {
		return serviceDeliveryPointRepository;
	}

	public void setServiceDeliveryPointRepository(
			ServiceDeliveryPointRepository serviceDeliveryPointRepository) {
		this.serviceDeliveryPointRepository = serviceDeliveryPointRepository;
	}

	public PrepaymentExceptionRepository getPrepaymentExceptionRepository() {
		return prepaymentExceptionRepository;
	}

	public void setPrepaymentExceptionRepository(
			PrepaymentExceptionRepository prepaymentExceptionRepository) {
		this.prepaymentExceptionRepository = prepaymentExceptionRepository;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public SdpMeterAssociationRepository getSdpMeterAssociationRepository() {
		return sdpMeterAssociationRepository;
	}

	public void setSdpMeterAssociationRepository(
			SdpMeterAssociationRepository sdpMeterAssociationRepository) {
		this.sdpMeterAssociationRepository = sdpMeterAssociationRepository;
	}

	public String getInactiveStatus() {
		return inactiveStatus;
	}

	public void setInactiveStatus(String inactiveStatus) {
		this.inactiveStatus = inactiveStatus;
	}
}
