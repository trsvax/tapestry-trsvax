package com.trsvax.tapestry.misc.services;

import org.slf4j.Logger;

public class ConstructorFactory implements NVLFactory {
	private final Class<?> clazz;
	private final Logger logger;
	
	public ConstructorFactory(Logger logger, Class<?> clazz) {
		this.clazz = clazz;
		this.logger = logger;
	}

	public Object newInstance() throws Exception {
		return clazz.newInstance();
	}

	public void persist(Object value) throws Exception {
		logger.info("persist {}",value);
		
	}

}
