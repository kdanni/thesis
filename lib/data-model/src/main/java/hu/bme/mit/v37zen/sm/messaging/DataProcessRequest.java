package hu.bme.mit.v37zen.sm.messaging;

import java.io.Serializable;

public interface DataProcessRequest<E extends Serializable> extends Serializable {

	public E getEntity();
}
