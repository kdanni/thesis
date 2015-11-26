package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import hu.bme.mit.v37zen.sm.datamodel.BaseEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tariff extends BaseEntity {

	private static final long serialVersionUID = 934067160228137368L;

	private double rate;
	
	private String unit;
	
	private boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
    
    private String status;
    
	private Boolean valid;
	
	private Boolean processed;
	
	private Boolean archived;
	
	public Tariff() {
		super();
	}

	public Tariff(double rate, String unit) {
		super();
		this.rate = rate;
		this.unit = unit;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result
				+ ((archived == null) ? 0 : archived.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((processed == null) ? 0 : processed.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((valid == null) ? 0 : valid.hashCode());
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
		Tariff other = (Tariff) obj;
		if (active != other.active)
			return false;
		if (archived == null) {
			if (other.archived != null)
				return false;
		} else if (!archived.equals(other.archived))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (processed == null) {
			if (other.processed != null)
				return false;
		} else if (!processed.equals(other.processed))
			return false;
		if (Double.doubleToLongBits(rate) != Double
				.doubleToLongBits(other.rate))
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
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tariff [rate=" + rate + ", unit=" + unit + ", active=" + active
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", valid=" + valid + ", processed="
				+ processed + ", archived=" + archived + ", id=" + id
				+ ", mRID=" + mRID + "]";
	}

	
}
