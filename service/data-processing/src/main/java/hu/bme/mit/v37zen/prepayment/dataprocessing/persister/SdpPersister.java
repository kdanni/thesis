package hu.bme.mit.v37zen.prepayment.dataprocessing.persister;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

public class SdpPersister implements MessageHandler {
	
	private SubscribableChannel channel;
	
	@Autowired
	private ServiceDeliveryPointRepository serviceDeliveryPointRepository;

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		
		Object payload = message.getPayload();
		if(payload instanceof ServiceDeliveryPoint){
			this.serviceDeliveryPointRepository.save((ServiceDeliveryPoint)payload);
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
}
