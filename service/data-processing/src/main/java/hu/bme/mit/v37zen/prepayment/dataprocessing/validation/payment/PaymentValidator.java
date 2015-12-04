package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.payment;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.AbstractValidator;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.AccountValidator;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;

public class PaymentValidator extends AbstractValidator{
	
	private static Logger logger = LoggerFactory.getLogger(PaymentValidator.class);
	
	@Autowired
	private AccountValidator accountValidator;
	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		if(this.validChannel == null || this.invalidChannel == null){
			if(this.validChannel == null){
				logger.warn("Valid channel is null.");
			}
			if(this.invalidChannel == null){
				logger.warn("Invalid Channel is null.");
			}
			return;
		}
		if(message == null || message.getPayload() == null 
				|| !(message.getPayload() instanceof Payment)){
			return;
		}
		Payment paymentData = (Payment)message.getPayload();
		try {
			if(!accountValidator.isAccountExist(paymentData.getAccountId())){
				throw new ValidationException("No Account exist with id: " + paymentData.getAccountId() , paymentData);
			}
			if(!accountValidator.isAccountActive(paymentData.getAccountId())){
				throw new ValidationException("No active Account found with id: " + paymentData.getAccountId() , paymentData);
			}
			
			paymentData.setValid(true);
			this.validChannel.send(new GenericMessage<Payment>(paymentData));
			
			//logger.debug("Valid Payment Data: " + paymentData.toString());
			
		} catch (ValidationException e) {
			logValidationException(e);
			this.invalidChannel.send(new GenericMessage<ValidationException>(e));
		}
		catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	@Transactional
	protected void logValidationException(ValidationException e){
		logger.info("[ValidationException]: " + e.getMessage());
		PrepaymentException pe = new PrepaymentException(new Date(), e.getMessage());
		prepaymentExceptionRepository.save(pe);
	}
	
	public AccountValidator getAccountValidator() {
		return accountValidator;
	}

	public void setAccountValidator(AccountValidator accountValidator) {
		this.accountValidator = accountValidator;
	}

	public PrepaymentExceptionRepository getPrepaymentExceptionRepository() {
		return prepaymentExceptionRepository;
	}

	public void setPrepaymentExceptionRepository(
			PrepaymentExceptionRepository prepaymentExceptionRepository) {
		this.prepaymentExceptionRepository = prepaymentExceptionRepository;
	}

}
