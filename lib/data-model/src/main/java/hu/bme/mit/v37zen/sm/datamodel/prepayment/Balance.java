package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import hu.bme.mit.v37zen.sm.datamodel.BaseEntity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Balance extends BaseEntity {

	private static final long serialVersionUID = -6039281388505532218L;

	private double balance;
	
	private double prepayed;
	
	private double consumption;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="prepaymentAccount_id")
	private PrepaymentAccount prepaymentAccount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public Balance() {
		super();
	}

	public Balance(double balance, Date date) {
		super();
		this.balance = balance;
		this.date = date;
	}

	public Balance(double balance, double prepayed, double consumption,
			Date date) {
		super();
		this.balance = balance;
		this.prepayed = prepayed;
		this.consumption = consumption;
		this.date = date;
	}
	
	public Balance(double balance, double prepayed, double consumption,
			PrepaymentAccount prepaymentAccount, Date date) {
		super();
		this.balance = balance;
		this.prepayed = prepayed;
		this.consumption = consumption;
		this.prepaymentAccount = prepaymentAccount;
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

	public double getPrepayed() {
		return prepayed;
	}

	public void setPrepayed(double prepayed) {
		this.prepayed = prepayed;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public PrepaymentAccount getPrepaymentAccount() {
		return prepaymentAccount;
	}

	public void setPrepaymentAccount(PrepaymentAccount prepaymentAccount) {
		this.prepaymentAccount = prepaymentAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(consumption);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		temp = Double.doubleToLongBits(prepayed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((prepaymentAccount == null) ? 0 : prepaymentAccount
						.hashCode());
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
		if (Double.doubleToLongBits(consumption) != Double
				.doubleToLongBits(other.consumption))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (Double.doubleToLongBits(prepayed) != Double
				.doubleToLongBits(other.prepayed))
			return false;
		if (prepaymentAccount == null) {
			if (other.prepaymentAccount != null)
				return false;
		} else if (!prepaymentAccount.equals(other.prepaymentAccount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Balance [balance=" + balance + ", prepayed=" + prepayed
				+ ", consumption=" + consumption + ", prepaymentAccount="
				+ prepaymentAccount + ", date=" + date + ", id=" + id
				+ ", mRID=" + mRID + "]";
	}
	
}