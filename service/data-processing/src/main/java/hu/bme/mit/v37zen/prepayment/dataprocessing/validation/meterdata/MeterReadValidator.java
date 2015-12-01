package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.meterdata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.Validator;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.AccountValidator;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.MeterAsssetValidator;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;

public class MeterReadValidator implements Validator<IntervalReading> {
	
	private static Logger logger = LoggerFactory.getLogger(MeterReadValidator.class);
	
	private IntervalReading meterData;
	
	private MessageChannel invalidChannel; 
	private MessageChannel validChannel; 
	
	@Autowired
	private MeterAsssetValidator meterAssetValidator;
	@Autowired
	private AccountValidator accountValidator;
	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	@Override
	public void run() {
		if(meterData == null){
			return;
		}
		String mRID = meterData.getMeterReferenceId();
		
		try {
			if(!meterAssetValidator.isMeterExist(mRID)){
				throw new ValidationException("Meter reading isn't valid. Can't found meter with mRID: " + mRID + ".", meterData); 
			}
			if(!accountValidator.isAccountActive(mRID)){
				throw new ValidationException("No active Account found with id: " + mRID , meterData);
			}
			accountValidator.getPrepaymentAccountByMeterAsset(mRID);
			//Exception is thrown if no valid Meter-SDP-Account relation exist.
			
			meterData.setValid(true);
			this.validChannel.send(new GenericMessage<IntervalReading>(meterData));
			
			//logger.debug("Valid Meter Data: " + meterData.toString());
			
		} catch (ValidationException e) {
			logValidationException(e);
			this.invalidChannel.send(new GenericMessage<IntervalReading>(meterData));
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
	public void setData(IntervalReading meterData) {
		this.meterData = meterData;
	}

	public MessageChannel getInvalidChannel() {
		return invalidChannel;
	}

	public void setInvalidChannel(MessageChannel invalidChannel) {
		this.invalidChannel = invalidChannel;
	}

	public MessageChannel getValidChannel() {
		return validChannel;
	}

	public void setValidChannel(MessageChannel validChannel) {
		this.validChannel = validChannel;
	}

	public AccountValidator getAccountValidator() {
		return accountValidator;
	}

	public void setAccountValidator(AccountValidator accountValidator) {
		this.accountValidator = accountValidator;
	}

	
}
