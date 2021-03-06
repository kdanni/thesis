package hu.bme.mit.v37zen.prepayment.dataprocessing.derivation;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.jpa.repositories.PaymentRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentAccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PaymentDerivator implements MessageHandler {

	public static final Logger logger = LoggerFactory.getLogger(PaymentDerivator.class);
	
	private SubscribableChannel channel;
	
	private MessageChannel outputChannel;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PrepaymentAccountRepository prepaymentAccountRepository;
	
	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	

	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		Object payload = message.getPayload();
		if(payload instanceof Payment){			
			try {
				String ppaccId = persistTransaction((Payment)payload);
				outputChannel.send(new GenericMessage<String>(ppaccId));
			
			} catch (ValidationException e) {
				logValidationException(e);
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}	
		}
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED,rollbackFor={ValidationException.class},transactionManager="transactionManager")
	protected synchronized String persistTransaction(Payment payment) throws ValidationException{
				
		String accMRID = payment.getAccountId();
		payment = new Payment(payment);
		payment.setInsertTime(new Date());
		payment.setProcessed(false);
		payment.setArchived(false);
				
		this.paymentRepository.save(payment);
		
		logger.debug("Payment derived: " + payment);
		return accMRID;
	}	
	
	@Transactional
	protected void logValidationException(ValidationException e){
		logger.info("[ValidationException]: " + e.getMessage());
		PrepaymentException pe = new PrepaymentException(new Date(), e.getMessage());
		prepaymentExceptionRepository.save(pe);
	}

	public SubscribableChannel getChannel() {
		return channel;
	}

	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		this.channel.subscribe(this);
	}

	public MessageChannel getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(MessageChannel outputChannel) {
		this.outputChannel = outputChannel;
	}
	public PaymentRepository getPaymentRepository() {
		return paymentRepository;
	}
	public void setPaymentRepository(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}
}
