package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;

public interface Validator extends MessageHandler {

	public void setChannel(SubscribableChannel subscribableChannel);
	
	public MessageChannel getInvalidChannel();

	public void setInvalidChannel(MessageChannel invalidChannel);

	public MessageChannel getValidChannel();

	public void setValidChannel(MessageChannel validChannel);

	public MessageChannel getRevalidationChannel();

	public void setRevalidationChannel(MessageChannel revalidationChannel);
}
