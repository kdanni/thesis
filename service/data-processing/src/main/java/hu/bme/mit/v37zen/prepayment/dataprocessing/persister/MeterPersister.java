package hu.bme.mit.v37zen.prepayment.dataprocessing.persister;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.jpa.repositories.MeterAssetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

public class MeterPersister implements MessageHandler {
	
	private SubscribableChannel channel;
	
	@Autowired
	private MeterAssetRepository meterAssetRepository;
	
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		
		Object payload = message.getPayload();
		if(payload instanceof MeterAsset){
			this.meterAssetRepository.save((MeterAsset)payload);
		}
	}

	public SubscribableChannel getChannel() {
		return channel;
	}
	
	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		channel.subscribe(this);
	}

	public MeterAssetRepository getMeterAssetRepository() {
		return meterAssetRepository;
	}

	public void setMeterAssetRepository(MeterAssetRepository meterAssetRepository) {
		this.meterAssetRepository = meterAssetRepository;
	}
}
