package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import hu.bme.mit.v37zen.sm.datamodel.BaseEntity;
import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PrepaymentAccount extends BaseEntity {

	private static final long serialVersionUID = 9119206955995611026L;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="accountSDPAssociation_id")
	private AccountSDPAssociation accountSDPAssociation;
	
	private String sdpMRID;
	private String accountMRID;
	
	private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
    
    private String status;
    
    @OneToMany(fetch=FetchType.LAZY)
    private List<Payment> payments = new ArrayList<Payment>();
    
    @OneToMany(fetch=FetchType.LAZY)
    private List<Balance> balance = new ArrayList<Balance>();
    
    @OneToMany(fetch=FetchType.LAZY)
    private List<IntervalReading> meterReadings = new ArrayList<IntervalReading>();
    
    public PrepaymentAccount() {
		super();
	}

	public PrepaymentAccount(AccountSDPAssociation accountSDPAssociation,
			boolean active, Date startDate, Date endDate, String status) {
		super();
		this.setAccountSDPAssociation(accountSDPAssociation);
		this.active = active;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public AccountSDPAssociation getAccountSDPAssociation() {
		return accountSDPAssociation;
	}

	public void setAccountSDPAssociation(AccountSDPAssociation accountSDPAssociation) {
		this.accountSDPAssociation = accountSDPAssociation;
		this.accountMRID = accountSDPAssociation.getAccountMRID();
		this.sdpMRID = accountSDPAssociation.getSdpMRID();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((accountMRID == null) ? 0 : accountMRID.hashCode());
		result = prime
				* result
				+ ((accountSDPAssociation == null) ? 0 : accountSDPAssociation
						.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((sdpMRID == null) ? 0 : sdpMRID.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrepaymentAccount other = (PrepaymentAccount) obj;
		if (accountMRID == null) {
			if (other.accountMRID != null)
				return false;
		} else if (!accountMRID.equals(other.accountMRID))
			return false;
		if (accountSDPAssociation == null) {
			if (other.accountSDPAssociation != null)
				return false;
		} else if (!accountSDPAssociation.equals(other.accountSDPAssociation))
			return false;
		if (active != other.active)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (sdpMRID == null) {
			if (other.sdpMRID != null)
				return false;
		} else if (!sdpMRID.equals(other.sdpMRID))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrepaymentAccount [accountSDPAssociation="
				+ accountSDPAssociation + ", sdpMRID=" + sdpMRID
				+ ", accountMRID=" + accountMRID + ", active=" + active
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", id=" + id + ", mRID=" + mRID + "]";
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public List<Balance> getBalance() {
		return balance;
	}

	public List<IntervalReading> getMeterReadings() {
		return meterReadings;
	}
    
    
}
