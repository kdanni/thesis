package hu.bme.mit.v37zen.sm.jpa.datamodel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccountContactAssociation extends BaseAssociation {

	private static final long serialVersionUID = -9057801870380268441L;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contact_id")
	private Contact contact;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	private String contactMRID;
	private String accountMRID;
	
		
	public AccountContactAssociation() {
		super();
	}
	public AccountContactAssociation(Date startDate, String status, String contactMRID, String accountMRID) {
		super(startDate, status);
		this.contactMRID = contactMRID;
		this.accountMRID = accountMRID;
	}
	
	
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getContactMRID() {
		return contactMRID;
	}
	public void setContactMRID(String contactMRID) {
		this.contactMRID = contactMRID;
	}
	public String getAccountMRID() {
		return accountMRID;
	}
	public void setAccountMRID(String accountMRID) {
		this.accountMRID = accountMRID;
	}		
	
	
	@Override
	public String toString() {
		return "AccountSDPAssociation [contactMRID=" + contactMRID + ", accountMRID="
				+ accountMRID + ", id=" + id + ", startDate=" + startDate
				+ ", status=" + status + "]";
	}
	
}
