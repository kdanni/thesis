package hu.bme.mit.v37zen.prepayment.dataprocessing.persister;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpMeterAssociationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

public class SdpMeterAssociationPersister implements MessageHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(SdpMeterAssociationPersister.class);
	
	private SubscribableChannel channel;
	
	@Autowired
	private SdpMeterAssociationRepository sdpMeterAssociationRepository;
	
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		
		Object payload = message.getPayload();
		if(payload instanceof SdpMeterAssociation){
			logger.debug(payload.toString());
			sdpMeterAssociationRepository.save((SdpMeterAssociation)payload);
		}
	}

	public SubscribableChannel getChannel() {
		return channel;
	}

	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		this.channel.subscribe(this);
	}

}
