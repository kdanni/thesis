package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.meterdata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.AbstractValidator;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.AccountValidator;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.MeterAsssetValidator;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.jpa.repositories.IntervalReadingRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;

public class MeterReadValidator extends AbstractValidator {
	
	private static Logger logger = LoggerFactory.getLogger(MeterReadValidator.class);
	
	@Value("${validation.meterread.valid.readtype}")
	private String validReadingType;
	
	@Autowired
	private MeterAsssetValidator meterAssetValidator;
	@Autowired
	private AccountValidator accountValidator;
	@Autowired
	private IntervalReadingRepository intervalReadingRepository;
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
				|| !(message.getPayload() instanceof IntervalReading)){
			return;
		}	
		IntervalReading meterData = (IntervalReading)message.getPayload();
		try {
			String mRID = meterData.getMeterReferenceId();
			
			if(!meterAssetValidator.isMeterExist(mRID)){
				throw new ValidationException("Meter reading isn't valid. Can't found meter with mRID: " + mRID + ".", meterData); 
			}
			PrepaymentAccount ppacc = accountValidator.getPrepaymentAccountByMeterAsset(mRID);
			//Exception is thrown if no valid Meter-SDP-Account relation exist.
			if(!accountValidator.isAccountActive(ppacc.getAccountMRID())){
				throw new ValidationException("No active Account found with id: " + mRID , meterData);
			}
			
			if(meterData.getReadingTypeId() == null || !meterData.getReadingTypeId().equalsIgnoreCase(validReadingType)){
				throw new ValidationException("Reading type not valid!", meterData);
			}
			
			List<IntervalReading> irl = this.intervalReadingRepository.findByMeterReferenceIdAndEndTime(mRID, meterData.getEndTime());
			if(irl.size() > 0){
				throw new ValidationException("Redundant IntervalReading!", meterData);
			}
			
			meterData.setValid(true);
			this.validChannel.send(new GenericMessage<IntervalReading>(meterData));
			
			//logger.debug("Valid Meter Data: " + meterData.toString());
			
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

	public String getValidReadingType() {
		return validReadingType;
	}

	public void setValidReadingType(String validReadingType) {
		this.validReadingType = validReadingType;
	}	
}
