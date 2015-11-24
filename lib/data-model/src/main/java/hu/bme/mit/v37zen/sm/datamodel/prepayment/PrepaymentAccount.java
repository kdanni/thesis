package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PrepaymentAccount extends BaseEntity {

	private static final long serialVersionUID = 9119206955995611026L;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="accountSDPAssociation_id")
	private AccountSDPAssociation accountSDPAssociation;
	
	boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
    
    private String status;
    
    public PrepaymentAccount() {
		super();
	}

	public PrepaymentAccount(AccountSDPAssociation accountSDPAssociation,
			boolean active, Date startDate, Date endDate, String status) {
		super();
		this.accountSDPAssociation = accountSDPAssociation;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((accountSDPAssociation == null) ? 0 : accountSDPAssociation
						.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
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
				+ accountSDPAssociation + ", active=" + active + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status
				+ ", id=" + id + ", mRID=" + mRID + "]";
	}
    
    
}
