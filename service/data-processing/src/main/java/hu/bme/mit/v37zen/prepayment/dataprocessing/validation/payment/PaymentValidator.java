package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.payment;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.Validator;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.AccountValidator;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;

public class PaymentValidator implements Validator<Payment>{
	
	private static Logger logger = LoggerFactory.getLogger(PaymentValidator.class);
	
	private Payment paymentData;
	
	private MessageChannel invalidChannel; 
	private MessageChannel validChannel; 
	
	@Autowired
	private AccountValidator accountValidator;
	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	@Override
	public void run() {
		if(paymentData == null){
			return;
		}		
		try {
			if(!accountValidator.isAccountExist(paymentData.getAccountId())){
				throw new ValidationException("No Account exist with id: " + paymentData.getAccountId() , paymentData);
			}
			
			paymentData.setValid(true);
			this.validChannel.send(new GenericMessage<Payment>(paymentData));
			
			//logger.debug("Valid Payment Data: " + paymentData.toString());
			
		} catch (ValidationException e) {
			logValidationException(e);
			this.invalidChannel.send(new GenericMessage<Payment>(paymentData));
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
	
	@Override
	public void setData(Payment paymentData) {
		this.paymentData = paymentData;		
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

	public MessageChannel getValidChannel() {
		return validChannel;
	}

	public void setValidChannel(MessageChannel validChannel) {
		this.validChannel = validChannel;
	}

	public MessageChannel getInvalidChannel() {
		return invalidChannel;
	}

	public void setInvalidChannel(MessageChannel invalidChannel) {
		this.invalidChannel = invalidChannel;
	}

}
