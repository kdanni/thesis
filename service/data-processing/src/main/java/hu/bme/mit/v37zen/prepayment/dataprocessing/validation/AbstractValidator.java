package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public abstract class AbstractValidator implements Validator{

	protected SubscribableChannel channel;
	
	protected MessageChannel invalidChannel;
	
	protected MessageChannel validChannel; 
	
	protected MessageChannel revalidationChannel; 
	
	
	@Override
	public void setChannel(SubscribableChannel subscribableChannel) {
		this.channel = subscribableChannel;
		this.channel.subscribe(this);
	}
	
	public SubscribableChannel getChannel(){
		return this.channel;
	}
	
	@Override
	public MessageChannel getInvalidChannel() {
		return invalidChannel;
	}

	@Override
	public void setInvalidChannel(MessageChannel invalidChannel) {
		this.invalidChannel = invalidChannel;
	}

	@Override
	public MessageChannel getValidChannel() {
		return validChannel;
	}

	@Override
	public void setValidChannel(MessageChannel validChannel) {
		this.validChannel = validChannel;
	}

	@Override
	public MessageChannel getRevalidationChannel() {
		return revalidationChannel;
	}

	@Override
	public void setRevalidationChannel(MessageChannel revalidationChannel) {
		this.revalidationChannel = revalidationChannel;
	}
}