package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

public interface Validator<E> extends Runnable {

	public void setData(E data);
	
}
