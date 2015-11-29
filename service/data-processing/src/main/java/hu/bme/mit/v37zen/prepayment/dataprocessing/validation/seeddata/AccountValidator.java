package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentAccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpMeterAssociationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator {
	
	@Value("${validation.association.active.status}")
	private String activeStatus;
	
	@Value("${validation.association.inactive.status}")
	private String inactiveStatus;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private SdpMeterAssociationRepository sdpMeterAssociationRepository;

	@Autowired
	private PrepaymentAccountRepository prepaymentAccountRepository;

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
	
	public PrepaymentAccount getPrepaymentAccountByMeterAsset(String meterMRID) throws ValidationException{
		
		List<SdpMeterAssociation> sdpMeterAssList = sdpMeterAssociationRepository.findByMeterAssetMRID(meterMRID);
		if(sdpMeterAssList.size() == 0){
			String msg = "No SdpMeterAssetAssociation found with the given id!";
			throw new ValidationException(msg);
		}
		SdpMeterAssociation activeSdpMeterAssociation = null;
		int activeCount = 0;
		for (SdpMeterAssociation sdpMeterAssociation : sdpMeterAssList) {
			if(sdpMeterAssociation.getStatus() != null && sdpMeterAssociation.getStatus().equalsIgnoreCase(activeStatus)){
				activeSdpMeterAssociation = sdpMeterAssociation;
				activeCount++; 
			}
		}
		if(activeCount != 1){
			String msg = (activeCount > 1) ? "Multiple active SdpMeterAssetAssociation found!"
					: "No active SdpMeterAssetAssociation found!";
			throw new ValidationException(msg);
		}
		String sdpMRID = null;
		if(activeSdpMeterAssociation != null){
			sdpMRID = activeSdpMeterAssociation.getSdpMRID();
		}
		
		List<PrepaymentAccount> ppaccList = prepaymentAccountRepository.findBySdpMRID(sdpMRID);
		if(ppaccList.size() == 0){
			String msg = "No PrepaymentAccount found for the SDP id: " + sdpMRID +".";
			throw new ValidationException(msg);
		}
		PrepaymentAccount ppacc = null;
		if(ppaccList.size() > 1){
			int active = 0;
			for (PrepaymentAccount prepaymentAccount : ppaccList) {
				if(prepaymentAccount.getStatus() != null && 
						prepaymentAccount.getAccountSDPAssociation().getStatus().equalsIgnoreCase(activeStatus)){
					ppacc = prepaymentAccount;
					active++;
				}
			}
			if(active != 1){
				String msg = ((active > 1) ? "Multiple" : "No") + " active PrepaymentAccount found for the SDP id: " + sdpMRID +".";
				throw new ValidationException(msg);
			}
		}
		
		return ppacc;
	}
	
	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public SdpMeterAssociationRepository getSdpMeterAssociationRepository() {
		return sdpMeterAssociationRepository;
	}

	public void setSdpMeterAssociationRepository(
			SdpMeterAssociationRepository sdpMeterAssociationRepository) {
		this.sdpMeterAssociationRepository = sdpMeterAssociationRepository;
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
	
	
	
}
