package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.AccountValidator;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;
import hu.bme.mit.v37zen.sm.messaging.SeedData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("unused")
public class SeedDataValidator implements Validator<SeedData>, ApplicationContextAware{
	
	private static Logger logger = LoggerFactory.getLogger(SeedDataValidator.class);
	
	private ApplicationContext applicationContext;
	
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
		
	private SeedData seedData;
	
	private HashMap<String, Account> accountCache = new HashMap<String, Account>();
	
	private AccountValidator accountValidator;
	
	@Override
	public void run() {
		
		List<Account> accList = seedData.getAccounts();
		if(accList.size() > 0){
			this.seedDataHasAccount(accList);						
		}
		List<AccountSDPAssociation> accSdpList = seedData.getAccountSDPAssociations();
		if(accSdpList.size() > 0){
			this.seedDataHasAccountSDPAssociation(accSdpList);
		}
		
		
	}
	
	protected void seedDataHasAccount(List<Account> accList){
		
		for (Account account : accList) {
			try {
				accountValidator.validate(account);
								
				accountCache.put(account.getMRID(), account);
				
				logger.info("Valid Account: " + account.toString());
				
			} catch (ValidationException e) {
				logValidationException(e);
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}		
		}
	}
	
	protected void seedDataHasAccountSDPAssociation(List<AccountSDPAssociation> accSdpList){
		
		for (AccountSDPAssociation accountSDPAssociation : accSdpList) {
			try{
				//TODO remove this:
				throw new ValidationException("");
				
				
				
				
			} catch (ValidationException e) {
				logValidationException(e);
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}		
	}
		
	
	protected void seedDataHasSDP(List<ServiceDeliveryPoint> sdpList){
		
		for (ServiceDeliveryPoint serviceDeliveryPoint : sdpList) {
			try{
				//TODO remove this:
				throw new ValidationException("");
				
				
				
				
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;		
		
		this.prepaymentExceptionRepository = applicationContext.getBean(PrepaymentExceptionRepository.class);
	}

	@Override
	public void setData(SeedData seedData) {
		this.seedData = seedData;
	}

	public AccountValidator getAccountValidator() {
		return accountValidator;
	}

	public void setAccountValidator(AccountValidator accountValidator) {
		this.accountValidator = accountValidator;
	}

}
