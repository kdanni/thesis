package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Balance extends BaseEntity {

	private static final long serialVersionUID = -6039281388505532218L;

	private double balance;
	
	private Date date;
	
	public Balance() {
		super();
	}

	public Balance(double balance, Date date) {
		super();
		this.balance = balance;
		this.date = date;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Balance other = (Balance) obj;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Balance [balance=" + balance + ", date=" + date + ", id=" + id
				+ ", mRID=" + mRID + "]";
	}
	
	
}
