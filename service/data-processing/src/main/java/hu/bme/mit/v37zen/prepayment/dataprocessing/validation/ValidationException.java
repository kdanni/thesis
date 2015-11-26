package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1461678755741126627L;

	private Object invalidObject;
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(String message, Object invalidObject) {
		super(message);
		this.invalidObject = invalidObject;
	}

	public Object getInvalidObject() {
		return invalidObject;
	}

	public void setInvalidObject(Object invalidObject) {
		this.invalidObject = invalidObject;
	}
	
}
