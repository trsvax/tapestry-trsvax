package com.trsvax.tapestry.misc.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class NVLServiceImpl implements NVLService {
	private final Map<Class,NVLFactory> classFactories;
	private final Map<String,NVLFactory> stringFactories;
	
	public NVLServiceImpl(Map<Class,NVLFactory> factories) {
		this.classFactories = factories;
		stringFactories = new HashMap<String, NVLFactory>(classFactories.size());
		for ( Entry<Class,NVLFactory> e : factories.entrySet() ) {
			stringFactories.put(e.getKey().getName(), e.getValue());
		}
	}

	public boolean isImplemented(String type) {
		return stringFactories.containsKey(type);
	}

	public Object newInstance(String type) {
		try {
			return stringFactories.get(type).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public void persist(String type, Object value) {
		try {
			stringFactories.get(type).persist(value);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	

}
