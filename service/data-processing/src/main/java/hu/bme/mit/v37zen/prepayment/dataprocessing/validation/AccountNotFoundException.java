package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;

public class AccountNotFoundException extends ValidationException{

	private static final long serialVersionUID = -3055023764405556387L;

	private String mRID;
	private Account account;
	
	public AccountNotFoundException(String mRID) {
		super("Account not found!");
		this.mRID = mRID;
	}
	
	public AccountNotFoundException(Account account) {
		super("Account not found!");
		this.account = account;
		this.setInvalidObject(account);
		if(account != null){
			this.mRID = account.getMRID();
		}
	}

	public String getmRID() {
		return mRID;
	}

	public void setmRID(String mRID) {
		this.mRID = mRID;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
