package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Payment extends BaseEntity {

	private static final long serialVersionUID = 5346031537413666843L;

	private double value;
	
	private String curency;
	
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
		result = prime * result + ((curency == null) ? 0 : curency.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [value=" + value + ", curency=" + curency + ", date="
				+ date + ", id=" + id + ", mRID=" + mRID + "]";
	}

}
