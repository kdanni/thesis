package hu.bme.mit.v37zen.sm.messaging.impl;

import java.io.Serializable;

import hu.bme.mit.v37zen.sm.messaging.DataProcessRequest;

public class BasicDataProcessRequest<E extends Serializable> implements DataProcessRequest<E> {

	private static final long serialVersionUID = 2287194503800838958L;

	private E entity;
	
	public BasicDataProcessRequest(E entity) {
		this.entity = entity;
	}
	
	@Override
	public E getEntity() {
		return entity;
	}

	
}
