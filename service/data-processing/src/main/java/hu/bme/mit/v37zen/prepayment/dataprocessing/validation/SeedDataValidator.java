package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.AccountValidator;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;
import hu.bme.mit.v37zen.sm.messaging.SeedData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("unused")
public class SeedDataValidator implements Validator<SeedData>, ApplicationContextAware{
	
	private static Logger logger = LoggerFactory.getLogger(SeedDataValidator.class);
	
	private ApplicationContext applicationContext;
		
	private SeedData seedData;
	
	private AccountValidator accountValidator;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public void run() {
		
		List<Account> accList = seedData.getAccounts();
		
		if(accList.size() > 0){
			for (Account account : accList) {
				try {
					if(accountValidator.isAccountExist(account.getMRID())){
						List<Account> list = accountRepository.findByMRID(account.getMRID());
						Account existingAccount = list.size() > 0 ? list.get(0) : null;
						
						
						
						
						
					} else {
						
						
					}	
					
				} catch (ValidationException e) {
					logger.info(e.getMessage());
					PrepaymentExceptionRepository repo = applicationContext.getBean(PrepaymentExceptionRepository.class);
					PrepaymentException pe = new PrepaymentException(new Date(), e.getMessage());
					repo.save(pe);
				}
				catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
				
			}			
		}
		
		
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;		
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

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
