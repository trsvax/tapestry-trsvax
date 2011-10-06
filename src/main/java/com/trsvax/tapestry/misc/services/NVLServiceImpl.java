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
