package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import javax.persistence.Entity;

@Entity
public class Tariff extends BaseEntity {

	private static final long serialVersionUID = 934067160228137368L;

	private double rate;
	
	private String unit;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(rate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		if (Double.doubleToLongBits(rate) != Double
				.doubleToLongBits(other.rate))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tariff [rate=" + rate + ", unit=" + unit + ", id=" + id
				+ ", mRID=" + mRID + "]";
	}
	
	
}
