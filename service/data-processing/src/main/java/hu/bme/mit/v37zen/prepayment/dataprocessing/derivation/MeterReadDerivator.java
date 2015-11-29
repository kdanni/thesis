package hu.bme.mit.v37zen.prepayment.dataprocessing.derivation;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.AccountValidator;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.jpa.repositories.IntervalReadingRepository;
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

public class MeterReadDerivator implements MessageHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(MeterReadDerivator.class);
		
	private SubscribableChannel channel;
	
	private MessageChannel outputChannel;

	@Autowired
	private AccountValidator accountValidator;
	
	@Autowired
	private PrepaymentAccountRepository prepaymentAccountRepository;
	
	@Autowired
	private IntervalReadingRepository intervalReadingRepository;
	
	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;

	
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		Object payload = message.getPayload();
		if(payload instanceof IntervalReading){
			logger.debug(payload.toString());
		
			IntervalReading ir = (IntervalReading)payload;
			String meterMRID = ir.getMeterReferenceId();
			
			try {
				PrepaymentAccount ppacc = accountValidator.getPrepaymentAccountByMeterAsset(meterMRID);

				ir = this.intervalReadingRepository.save(ir);
				ppacc.getMeterReadings().add(ir);
				
				ppacc = this.prepaymentAccountRepository.save(ppacc);			
				
				outputChannel.send(new GenericMessage<PrepaymentAccount>(ppacc));
				
			} catch (ValidationException e) {
				logValidationException(e);
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}		
		}
	}
	
	protected void logValidationException(ValidationException e){
		logger.info("[ValidationException]: " + e.getMessage());
		PrepaymentException pe = new PrepaymentException(new Date(), e.getMessage());
		prepaymentExceptionRepository.save(pe);
	}

	public MessageChannel getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(MessageChannel outputChannel) {
		this.outputChannel = outputChannel;
	}

	public SubscribableChannel getChannel() {
		return channel;
	}

	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
	}

	public IntervalReadingRepository getIntervalReadingRepository() {
		return intervalReadingRepository;
	}

	public void setIntervalReadingRepository(IntervalReadingRepository intervalReadingRepository) {
		this.intervalReadingRepository = intervalReadingRepository;
	}

	public PrepaymentExceptionRepository getPrepaymentExceptionRepository() {
		return prepaymentExceptionRepository;
	}

	public void setPrepaymentExceptionRepository(
			PrepaymentExceptionRepository prepaymentExceptionRepository) {
		this.prepaymentExceptionRepository = prepaymentExceptionRepository;
	}

	public PrepaymentAccountRepository getPrepaymentAccountRepository() {
		return prepaymentAccountRepository;
	}

	public void setPrepaymentAccountRepository(
			PrepaymentAccountRepository prepaymentAccountRepository) {
		this.prepaymentAccountRepository = prepaymentAccountRepository;
	}

	public AccountValidator getAccountValidator() {
		return accountValidator;
	}

	public void setAccountValidator(AccountValidator accountValidator) {
		this.accountValidator = accountValidator;
	}

}
