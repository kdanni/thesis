package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.AbstractValidator;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ScheduledForRevalidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;
import hu.bme.mit.v37zen.sm.messaging.SeedData;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;

public class SeedDataValidator extends AbstractValidator {
	
	public static final int REVALIDATION_THRESHOLD = 120;

	private static Logger logger = LoggerFactory.getLogger(SeedDataValidator.class);
	
	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	private HashMap<String, Account> accountCache = new HashMap<String, Account>();
	private HashMap<String, ServiceDeliveryPoint> sdpCache = new HashMap<String, ServiceDeliveryPoint>();
	private List<AccountSDPAssociation> accSdpCache = new ArrayList<AccountSDPAssociation>();
	private HashMap<String, MeterAsset> meterCache = new HashMap<String, MeterAsset>();
	private List<SdpMeterAssociation> sdpMeterCache = new ArrayList<SdpMeterAssociation>();
	
	@Autowired
	private AccountValidator accountValidator;
	@Autowired
	private SDPValidator sDPValidator;
	@Autowired
	private AccountSDPAssociationValidator accountSDPAssociationValidator;
	@Autowired
	private MeterAsssetValidator meterAssetValidator;
	@Autowired
	private SDPMeterAssociationValidator sDPMeterAssociationValidator;
	
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		if(this.validChannel == null || this.invalidChannel == null || this.revalidationChannel == null){
			if(this.validChannel == null){
				logger.warn("Valid channel is null.");
			}
			if(this.invalidChannel == null){
				logger.warn("Invalid Channel is null.");
			}
			if(this.revalidationChannel == null){
				logger.warn("Revalidation Channel is null.");
			}
			return;
		}
		if(message == null || message.getPayload() == null 
				|| !(message.getPayload() instanceof SeedData)){
			return;
		}
		
		SeedData seedData = (SeedData) message.getPayload();
		List<Account> accList = seedData.getAccounts();
		if(accList != null && accList.size() > 0){
			this.seedDataHasAccount(accList);						
		}
		List<ServiceDeliveryPoint> sdpList = seedData.getServiceDeliveryPoints();
		if(sdpList != null && sdpList.size() > 0){
			this.seedDataHasSDP(sdpList);						
		}
		List<AccountSDPAssociation> accSdpList = seedData.getAccountSDPAssociations();
		if(accSdpList != null && accSdpList.size() > 0){
			this.seedDataHasAccountSDPAssociation(accSdpList,seedData.getProcessed());
		}
		
		List<MeterAsset> meterAssets = seedData.getMeterAssets();
		if(meterAssets != null && meterAssets.size() > 0){
			this.seedDataHasMeter(meterAssets);
		}
		List<SdpMeterAssociation> sdpMeterList = seedData.getSdpMeterAssociations();
		if(sdpMeterList != null && sdpMeterList.size() > 0){
			this.seedDataHasSDPMeterAssociation(sdpMeterList,seedData.getProcessed());
		}
	}
	
	protected void seedDataHasAccount(List<Account> accList){
		
		for (Account account : accList) {
			try {
				accountValidator.validate(account);
								
				accountCache.put(account.getMRID(), account);
				
				validChannel.send(new GenericMessage<Account>(account));
				
				//logger.debug("Valid Account: " + account.toString());
				
			} catch (ValidationException e) {
				logValidationException(e);
				this.invalidChannel.send(new GenericMessage<ValidationException>(e));
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}		
		}
	}
	
	protected void seedDataHasSDP(List<ServiceDeliveryPoint> sdpList){
		
		for (ServiceDeliveryPoint serviceDeliveryPoint : sdpList) {
			try{
				sDPValidator.validate(serviceDeliveryPoint);
				
				sdpCache.put(serviceDeliveryPoint.getMRID(), serviceDeliveryPoint);
				
				validChannel.send(new GenericMessage<ServiceDeliveryPoint>(serviceDeliveryPoint));
				
				//logger.debug("Valid SDP: " + serviceDeliveryPoint.toString());
				
			} catch (ValidationException e) {
				logValidationException(e);
				this.invalidChannel.send(new GenericMessage<ValidationException>(e));
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}	
		}		
	}
	
	protected void seedDataHasAccountSDPAssociation(List<AccountSDPAssociation> accSdpList, int processed){
		
		for (AccountSDPAssociation accountSDPAssociation : accSdpList) {
			try{
				accountSDPAssociationValidator.validate(accountSDPAssociation, this.accountCache, this.sdpCache);
				
				accSdpCache.add(accountSDPAssociation);
				
				validChannel.send(new GenericMessage<AccountSDPAssociation>(accountSDPAssociation));
				
				//logger.info("Valid AccountSDPAssociation: " + accountSDPAssociation.toString());
								
			} catch (ValidationException e) {
				logValidationException(e);
				this.invalidChannel.send(new GenericMessage<ValidationException>(e));
			} 
			catch (ScheduledForRevalidationException e) {
				logger.debug("AccountSDPAssociation scheduled for revalidation.");
				ArrayList<AccountSDPAssociation> al = new ArrayList<AccountSDPAssociation>();
				al.add(accountSDPAssociation);
				SeedData sd = new SeedData(null, null, null, null, null, null, null, al, null, null, null);
				sd.setProcessed(processed+1);
				if(processed > REVALIDATION_THRESHOLD){
					ValidationException ve = new ValidationException("AccountSDPAssociation reach revalidation treshhold.", accountSDPAssociation);
					logValidationException(ve);
					this.invalidChannel.send(new GenericMessage<ValidationException>(ve));
				}
				this.revalidationChannel.send(new GenericMessage<SeedData>(sd));
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}		
	}
	
	protected void seedDataHasMeter(List<MeterAsset> meterAssets) {
		
		for (MeterAsset meterAsset : meterAssets) {
			try{
				meterAssetValidator.validate(meterAsset);
				
				meterCache.put(meterAsset.getMRID(), meterAsset);

				validChannel.send(new GenericMessage<MeterAsset>(meterAsset));
				
				//logger.debug("Valid MeterAsset: " + meterAsset.toString());
				
			} catch (ValidationException e) {
				logValidationException(e);
				this.invalidChannel.send(new GenericMessage<ValidationException>(e));
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}	
		}	
	}

	private void seedDataHasSDPMeterAssociation(List<SdpMeterAssociation> sdpMeterList, int processed) {
		
		for (SdpMeterAssociation sdpMeterAssociation : sdpMeterList) {
			try{
				sDPMeterAssociationValidator.validate(sdpMeterAssociation, this.sdpCache, this.meterCache);
				
				sdpMeterCache.add(sdpMeterAssociation);
				
				validChannel.send(new GenericMessage<SdpMeterAssociation>(sdpMeterAssociation));
				
				//logger.info("Valid SdpMeterAssociation: " + sdpMeterAssociation.toString());
								
			} catch (ValidationException e) {
				logValidationException(e);
				this.invalidChannel.send(new GenericMessage<ValidationException>(e));
			} 
			catch (ScheduledForRevalidationException e) {
				logger.debug("SdpMeterAssociation scheduled for revalidation.");
				ArrayList<SdpMeterAssociation> al = new ArrayList<SdpMeterAssociation>();
				al.add(sdpMeterAssociation);
				SeedData sd = new SeedData(null, null, null, null, null, null, al, null, null, null, null);
				sd.setProcessed(processed+1);
				if(processed > REVALIDATION_THRESHOLD){
					ValidationException ve = new ValidationException("SdpMeterAssociation reach revalidation treshhold.", sdpMeterAssociation);
					logValidationException(ve);
					this.invalidChannel.send(new GenericMessage<ValidationException>(ve));					
				}
				this.revalidationChannel.send(new GenericMessage<SeedData>(sd));
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
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

	public AccountSDPAssociationValidator getAccountSDPAssociationValidator() {
		return accountSDPAssociationValidator;
	}

	public void setAccountSDPAssociationValidator(
			AccountSDPAssociationValidator accountSDPAssociationValidator) {
		this.accountSDPAssociationValidator = accountSDPAssociationValidator;
	}

	public PrepaymentExceptionRepository getPrepaymentExceptionRepository() {
		return prepaymentExceptionRepository;
	}

	public void setPrepaymentExceptionRepository(
			PrepaymentExceptionRepository prepaymentExceptionRepository) {
		this.prepaymentExceptionRepository = prepaymentExceptionRepository;
	}

	public SDPValidator getsDPValidator() {
		return sDPValidator;
	}

	public void setsDPValidator(SDPValidator sDPValidator) {
		this.sDPValidator = sDPValidator;
	}

	public SDPMeterAssociationValidator getsDPMeterAssociationValidator() {
		return sDPMeterAssociationValidator;
	}

	public void setsDPMeterAssociationValidator(
			SDPMeterAssociationValidator sDPMeterAssociationValidator) {
		this.sDPMeterAssociationValidator = sDPMeterAssociationValidator;
	}
}
