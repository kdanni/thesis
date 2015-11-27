package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator {

	@Autowired
	private AccountRepository accountRepository;

	public boolean isAccountExist(String mRID) throws ValidationException{
		
		List<Account> al = accountRepository.findByMRID(mRID);
		if(al.size() > 1){
			throw new ValidationException("More than one account exist with the mRID of " + mRID + ".", al);
		}
		return al.size() == 1;
	}
	
	public void validate(Account account) throws ValidationException{
		
		if(account.getMRID() == null || account.getMRID().trim().isEmpty()){
			throw new ValidationException("Account mRID is empty!", account);
		}	
		
	}
	
	
	
	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	
	
}
