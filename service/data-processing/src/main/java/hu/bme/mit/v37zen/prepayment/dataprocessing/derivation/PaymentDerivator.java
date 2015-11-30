package hu.bme.mit.v37zen.prepayment.dataprocessing.derivation;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.jpa.repositories.PaymentRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentAccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

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
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={ValidationException.class},transactionManager="transactionManager")
	protected synchronized String persistTransaction(Payment payment) throws ValidationException{
				
		String accMRID = payment.getAccountId();
		payment = new Payment(payment);
		payment.setInsertTime(new Date());
				
		payment = this.paymentRepository.save(payment);
		
		List<PrepaymentAccount> ppaccList = prepaymentAccountRepository.findByAccountMRID(accMRID);
		if(ppaccList.size() != 1){
			String msg = ((ppaccList.size() > 1) ? "Multiple" : "No") + " PrepaymentAccount found with id: " + accMRID +".";
			throw new ValidationException(msg);
		}
		PrepaymentAccount ppacc = ppaccList.get(0);
		String id = ppacc.getMRID();
		
		ppacc.getPayments().size();
		ppacc.getPayments().add(payment);
		
		this.prepaymentAccountRepository.save(ppacc);
		
		logger.debug("Payment derived: " + payment);
		
		return id;
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
