package hu.bme.mit.v37zen.sm.jpa.datamodel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccountSDPAssociation extends BaseAssociation {

	private static final long serialVersionUID = -7304964318373360301L;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sdp_id")
	private ServiceDeliveryPoint serviceDeliveryPoint;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	private String sdpMRID;
	private String accountMRID;
	
	public AccountSDPAssociation() {
		super();
	}

	public AccountSDPAssociation(Date startDate, String status) {
		super(startDate, status);
	}
	
	

	public AccountSDPAssociation(ServiceDeliveryPoint sdpDeliveryPoint,
			Account account) {
		super();
		this.setServiceDeliveryPoint(sdpDeliveryPoint);
		this.setAccount(account);
	}
	
	public AccountSDPAssociation(ServiceDeliveryPoint sdpDeliveryPoint,
			Account account, Date startDate, String status) {
		super(startDate, status);
		this.setServiceDeliveryPoint(sdpDeliveryPoint);
		this.setAccount(account);
	}

	public String getSdpMRID() {
		return sdpMRID;
	}

	public void setSdpMRID(String sdpMRID) {
		this.sdpMRID = sdpMRID;
	}

	public String getAccountMRID() {
		return accountMRID;
	}

	public void setAccountMRID(String accountMRID) {
		this.accountMRID = accountMRID;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.accountMRID = account.getMRID();
		this.account = account;
	}

	public ServiceDeliveryPoint getServiceDeliveryPoint() {
		return serviceDeliveryPoint;
	}

	public void setServiceDeliveryPoint(ServiceDeliveryPoint serviceDeliveryPoint) {
		this.sdpMRID = serviceDeliveryPoint.getMRID();
		this.serviceDeliveryPoint = serviceDeliveryPoint;
	}

	@Override
	public String toString() {
		return "AccountSDPAssociation [sdpMRID=" + sdpMRID + ", accountMRID="
				+ accountMRID + ", id=" + id + ", startDate=" + startDate
				+ ", status=" + status + "]";
	}



}
