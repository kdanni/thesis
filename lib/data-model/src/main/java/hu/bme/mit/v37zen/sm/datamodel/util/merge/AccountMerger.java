package hu.bme.mit.v37zen.sm.datamodel.util.merge;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;

public class AccountMerger {

	public static Account merge(Account returnee, Account theOtherAccount ){
				
		if(theOtherAccount.getAccountClass() != null && !theOtherAccount.getAccountClass().trim().isEmpty()){
			returnee.setAccountClass(theOtherAccount.getAccountClass());
		}
		if(theOtherAccount.getAccountType() != null && !theOtherAccount.getAccountType().trim().isEmpty()){
			returnee.setAccountType(theOtherAccount.getAccountType());
		}
		if(theOtherAccount.getName() != null && !theOtherAccount.getName().trim().isEmpty()){
			returnee.setName(theOtherAccount.getName());
		}
		if(theOtherAccount.getName2() != null && !theOtherAccount.getName2().trim().isEmpty()){
			returnee.setName2(theOtherAccount.getName2());
		}
		if(theOtherAccount.getPhoneNumber() != null && !theOtherAccount.getPhoneNumber().trim().isEmpty()){
			returnee.setPhoneNumber(theOtherAccount.getPhoneNumber());
		}
		if(theOtherAccount.getStatus() != null && !theOtherAccount.getStatus().trim().isEmpty()){
			returnee.setStatus(theOtherAccount.getStatus());
		}
				
		return returnee;
	}
}
