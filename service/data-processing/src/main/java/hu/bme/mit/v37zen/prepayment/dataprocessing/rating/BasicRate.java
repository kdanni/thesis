package hu.bme.mit.v37zen.prepayment.dataprocessing.rating;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.Tariff;

public class BasicRate implements Rate {

	public static final Tariff TARIFF = new Tariff(1000, null);
		
	@Override
	public double rate(double value) {
		
		return value * TARIFF.getRate();
	}

}
