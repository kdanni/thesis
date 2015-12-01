package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentAccountRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountValidator {
	
	@Value("${validation.association.active.status}")
	private String activeStatus;
	
	@Value("${validation.association.inactive.status}")
	private String inactiveStatus;

	@Autowired
	private MeterAsssetValidator meterAsssetValidator;
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PrepaymentAccountRepository prepaymentAccountRepository;

	public boolean isAccountExist(String mRID) throws ValidationException{
		
		List<Account> al = accountRepository.findByMRID(mRID);
		if(al.size() > 1){
			throw new ValidationException("More than one account exist with the mRID of " + mRID + ".", al);
		}
		return al.size() == 1;
	}
	
	public boolean isAccountActive(String mRID) throws ValidationException{
		
		List<PrepaymentAccount> al = prepaymentAccountRepository.findByAccountMRID(mRID);
		if(al.size() == 1){
			PrepaymentAccount ppacc = al.get(0);
			String status = ppacc.getStatus();			
			if(status != null && status.equalsIgnoreCase(activeStatus)){
				return true;
			}
			return false;
		}
		if(al.size() > 1){
			throw new ValidationException("More than one account exist with the mRID of " + mRID + ".", al);
		}
		throw new ValidationException("No account exist with the mRID of " + mRID + ".");
	}
	
	public void validate(Account account) throws ValidationException{
		
		if(account.getMRID() == null || account.getMRID().trim().isEmpty()){
			throw new ValidationException("Account mRID is empty!", account);
		}	
		
	}
	
	@Transactional
	public synchronized PrepaymentAccount getPrepaymentAccountByMeterAsset(String meterMRID) throws ValidationException{
		
		String sdpMRID = meterAsssetValidator.isActiveSdpMeterAssociationExist(meterMRID);
		List<PrepaymentAccount> ppaccList = prepaymentAccountRepository.findBySdpMRID(sdpMRID);
		if(ppaccList.size() == 0){
			String msg = "No PrepaymentAccount found for the SDP id: " + sdpMRID +".";
			throw new ValidationException(msg);
		}
		if(ppaccList.size() != 1){
			String msg = ((ppaccList.size() > 1) ? "Multiple" : "No") + " active PrepaymentAccount found for the SDP id: " + sdpMRID +".";
			throw new ValidationException(msg);
		}
		return ppaccList.get(0);
		
	}
	
	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public PrepaymentAccountRepository getPrepaymentAccountRepository() {
		return prepaymentAccountRepository;
	}

	public void setPrepaymentAccountRepository(
			PrepaymentAccountRepository prepaymentAccountRepository) {
		this.prepaymentAccountRepository = prepaymentAccountRepository;
	}

	public String getInactiveStatus() {
		return inactiveStatus;
	}

	public void setInactiveStatus(String inactiveStatus) {
		this.inactiveStatus = inactiveStatus;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public MeterAsssetValidator getMeterAsssetValidator() {
		return meterAsssetValidator;
	}

	public void setMeterAsssetValidator(MeterAsssetValidator meterAsssetValidator) {
		this.meterAsssetValidator = meterAsssetValidator;
	}	
}
