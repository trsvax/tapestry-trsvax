package com.trsvax.tapestry.misc.services;

public class ConstructorFactory implements InterfaceFactory {
	private final Class<?> clazz;
	
	public ConstructorFactory(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Object newInstance() throws Exception {
		return clazz.newInstance();
	}

}
