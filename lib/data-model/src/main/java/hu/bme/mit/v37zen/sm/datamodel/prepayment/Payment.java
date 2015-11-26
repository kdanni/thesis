package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import hu.bme.mit.v37zen.sm.datamodel.BaseEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Payment extends BaseEntity {

	private static final long serialVersionUID = 5346031537413666843L;

	private String accountId;
	
	private Boolean valid;
	
	private Boolean processed;
	
	private Boolean archived;
	
	private String status;
	
	private Double value;
	
	private String curency;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Payment() {
		super();
	}

	public Payment(double balance, String curency, Date date) {
		super();
		this.value = balance;
		this.date = date;
		this.curency = curency;
	}

	public Payment(String accountId, Boolean valid, Boolean processed,
			Boolean archived, String status, double value, String curency,
			Date date) {
		super();
		this.accountId = accountId;
		this.valid = valid;
		this.processed = processed;
		this.archived = archived;
		this.status = status;
		this.value = value;
		this.curency = curency;
		this.date = date;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurency() {
		return curency;
	}

	public void setCurency(String curency) {
		this.curency = curency;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result
				+ ((archived == null) ? 0 : archived.hashCode());
		result = prime * result + ((curency == null) ? 0 : curency.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((processed == null) ? 0 : processed.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((valid == null) ? 0 : valid.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Payment other = (Payment) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (archived == null) {
			if (other.archived != null)
				return false;
		} else if (!archived.equals(other.archived))
			return false;
		if (curency == null) {
			if (other.curency != null)
				return false;
		} else if (!curency.equals(other.curency))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (processed == null) {
			if (other.processed != null)
				return false;
		} else if (!processed.equals(other.processed))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [accountId=" + accountId + ", valid=" + valid
				+ ", processed=" + processed + ", archived=" + archived
				+ ", status=" + status + ", value=" + value + ", curency="
				+ curency + ", date=" + date + ", id=" + id + ", mRID=" + mRID
				+ "]";
	}

	

}
