package hu.bme.mit.v37zen.prepayment.dataprocessing.persister;

import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.datamodel.util.merge.SdpMerger;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.transaction.annotation.Transactional;

public class SdpPersister implements MessageHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(SdpPersister.class);
	
	private SubscribableChannel channel;
	
	@Autowired
	private ServiceDeliveryPointRepository serviceDeliveryPointRepository;

	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	@Override
	@Transactional
	public void handleMessage(Message<?> message) throws MessagingException {
		
		Object payload = message.getPayload();
		if(payload instanceof ServiceDeliveryPoint){
			ServiceDeliveryPoint sdp = (ServiceDeliveryPoint) payload;
			List<ServiceDeliveryPoint> sdpList = serviceDeliveryPointRepository.findByMRID(sdp.getMRID()); 
			
			if(sdpList.size() == 0){
				this.serviceDeliveryPointRepository.save(sdp);
			} else if (sdpList.size() == 1){
				sdp = SdpMerger.merge(sdpList.get(0), sdp);
				sdp = this.serviceDeliveryPointRepository.save(sdp);
			} else {
				String msg = "Multiple SDP found with the same id!";
				logger.error(msg);
				PrepaymentException pe = new PrepaymentException(new Date(), msg);
				getPrepaymentExceptionRepository().save(pe);
				return;
			}
		}
	}

	public SubscribableChannel getChannel() {
		return channel;
	}
	
	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		channel.subscribe(this);
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
}
