package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

public class ValidationMultiplexer {
	
	private SubscribableChannel invalidChannel;
	
	private SubscribableChannel validChannel; 
	
	private MessageChannel outputChannel;
	
	private ValidHandler validHandler;
	
	private InvalidHandler invalidHandler;
	
	public ValidationMultiplexer(SubscribableChannel validChannel, SubscribableChannel invalidChannel,
			 MessageChannel outputChannel) {
		this.validChannel = validChannel;
		this.invalidChannel = invalidChannel;
		this.invalidHandler = new InvalidHandler(outputChannel);
		this.validHandler = new ValidHandler(outputChannel);
		this.validChannel.subscribe(validHandler);
		this.invalidChannel.subscribe(invalidHandler);
	}
	
	protected class ValidHandler implements MessageHandler{
		private MessageChannel outputChannel;
		
		ValidHandler(MessageChannel outputChannel){
			this.outputChannel = outputChannel;
		}
		
		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			System.out.println("ValidationMultiplexer::ValidHandler::handleMessage()");
			if(this.outputChannel != null)
				this.outputChannel.send(new GenericMessage<Boolean>(Boolean.TRUE));
		}

		public MessageChannel getOutputChannel() {
			return outputChannel;
		}

		public void setOutputChannel(MessageChannel outputChannel) {
			this.outputChannel = outputChannel;
		}
		
	}
	
	protected class InvalidHandler implements MessageHandler{
		private MessageChannel outputChannel;
		
		public InvalidHandler(MessageChannel outputChannel) {
			this.outputChannel = outputChannel;
		}

		@Override
		public void handleMessage(Message<?> message) throws MessagingException {
			System.out.println("ValidationMultiplexer::InvalidHandler::handleMessage()");
			if(this.outputChannel != null)
				this.outputChannel.send(new GenericMessage<Boolean>(Boolean.FALSE));
		}

		public MessageChannel getOutputChannel() {
			return outputChannel;
		}

		public void setOutputChannel(MessageChannel outputChannel) {
			this.outputChannel = outputChannel;
		}
		
	}

	public SubscribableChannel getInvalidChannel() {
		return invalidChannel;
	}

	public void setInvalidChannel(SubscribableChannel invalidChannel) {
		this.invalidChannel.unsubscribe(invalidHandler);
		this.invalidChannel = invalidChannel;
		this.invalidChannel.subscribe(invalidHandler);
	}

	public SubscribableChannel getValidChannel() {
		return validChannel;
	}

	public void setValidChannel(SubscribableChannel validChannel) {
		this.validChannel.unsubscribe(validHandler);
		this.validChannel = validChannel;
		this.validChannel.subscribe(validHandler);
	}

	public MessageChannel getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(MessageChannel outputChannel) {
		this.outputChannel = outputChannel;
		this.validHandler.setOutputChannel(outputChannel);
		this.invalidHandler.setOutputChannel(outputChannel);
	}
}
