package hu.bme.mit.v37zen.prepayment.dataprocessing.reacting;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.Balance;
import hu.bme.mit.v37zen.sm.jpa.repositories.BalanceRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

public class Reactor implements MessageHandler{
	public static final Logger logger = LoggerFactory.getLogger(Reactor.class);
	
	private static final double THRESHOLD = 0;
	
	@Value("${validation.association.active.status}")
	private String activeStatus;
	
	@Value("${validation.association.inactive.status}")
	private String inactiveStatus;

	@Autowired
	private BalanceRepository balanceRepository;
	
	private SubscribableChannel channel;
	
	private MessageChannel mailChannel;
	
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		Object payload = message.getPayload();
		if(payload instanceof String){
			String mRID = (String) payload;
			try {
				logger.debug("Reacting..");
				List<Balance> result = balanceRepository.findByAccountIdOrderByDateDesc(mRID, new PageRequest(0, 1));
				if(result.size() == 1){
					Balance b = result.get(0);
					double balance = b.getBalance();
					
					if(balance < THRESHOLD){
						mailChannel.send(new GenericMessage<String>("Elfogyott a energia kereted!"));
						
					}
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}		
		}
		
	}

	public BalanceRepository getBalanceRepository() {
		return balanceRepository;
	}

	public void setBalanceRepository(BalanceRepository balanceRepository) {
		this.balanceRepository = balanceRepository;
	}

	public SubscribableChannel getChannel() {
		return channel;
	}

	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		this.channel.subscribe(this);
	}

	public MessageChannel getMailChannel() {
		return mailChannel;
	}

	public void setMailChannel(MessageChannel mailChannel) {
		this.mailChannel = mailChannel;
	}

}
