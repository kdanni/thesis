package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ScheduledForRevalidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountSDPAssociationValidator {

	@Autowired
	private AccountValidator accountValidator;
	@Autowired
	private SDPValidator sdpValidator;
	
	public void validate(AccountSDPAssociation accSdpAss , HashMap<String, Account> newAccounts,
			HashMap<String, ServiceDeliveryPoint> newSDPs) throws ValidationException, ScheduledForRevalidationException {
		
		String accMRID;
		if(accSdpAss.getAccount() != null){
			if(accSdpAss.getAccountMRID() != null && !accSdpAss.getAccountMRID().trim().isEmpty() ){
				if(accSdpAss.getAccount().getMRID() != accSdpAss.getAccountMRID()){
					throw new ValidationException("AccountSDPAssociation is inconsistent.", accSdpAss);
				} else {
					accMRID = accSdpAss.getAccount().getMRID();
				}
			} else{
				accMRID = accSdpAss.getAccount().getMRID();
			}			
		} else {
			accMRID = accSdpAss.getAccountMRID();
		}
		
		String sdpMRID;
		if(accSdpAss.getServiceDeliveryPoint() != null){
			if(accSdpAss.getSdpMRID() != null && !accSdpAss.getSdpMRID().trim().isEmpty() ){
				if(accSdpAss.getServiceDeliveryPoint().getMRID() != accSdpAss.getSdpMRID()){
					throw new ValidationException("AccountSDPAssociation is inconsistent.", accSdpAss);
				} else {
					sdpMRID = accSdpAss.getServiceDeliveryPoint().getMRID();
				}
			} else{
				sdpMRID = accSdpAss.getServiceDeliveryPoint().getMRID();
			}			
		} else {
			sdpMRID = accSdpAss.getSdpMRID();
		}

		boolean accOK = newAccounts.containsKey(accMRID);
		boolean sdpOK = newSDPs.containsKey(sdpMRID);
		
		if(!accOK){
			accOK = this.accountValidator.isAccountExist(accMRID);
		}
		if(!sdpOK){
			sdpOK = this.sdpValidator.isSdpExist(sdpMRID);
		}
		
		if(!accOK || !sdpOK){
			throw new ScheduledForRevalidationException(null);
		}
	}

	public AccountValidator getAccountValidator() {
		return accountValidator;
	}

	public void setAccountValidator(AccountValidator accountValidator) {
		this.accountValidator = accountValidator;
	}

	public SDPValidator getSdpValidator() {
		return sdpValidator;
	}

	public void setSdpValidator(SDPValidator sdpValidator) {
		this.sdpValidator = sdpValidator;
	}
}
