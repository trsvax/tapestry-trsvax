package com.trsvax.tapestry.misc.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class InterfaceImplmentationImpl implements InterfaceImplementation {
	private final Map<Class,InterfaceFactory> classFactories;
	private final Map<String,InterfaceFactory> stringFactories;
	
	public InterfaceImplmentationImpl(Map<Class,InterfaceFactory> factories) {
		this.classFactories = factories;
		stringFactories = new HashMap<String, InterfaceFactory>(classFactories.size());
		for ( Entry<Class,InterfaceFactory> e : factories.entrySet() ) {
			stringFactories.put(e.getKey().getName(), e.getValue());
		}
	}

	public boolean isImplemented(String interfaceName) {
		return stringFactories.containsKey(interfaceName);
	}

	public Object newInstance(String interfaceName) {
		try {
			return stringFactories.get(interfaceName).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
