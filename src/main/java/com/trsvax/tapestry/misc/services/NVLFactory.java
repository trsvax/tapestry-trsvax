package com.trsvax.tapestry.misc.services;

public interface NVLFactory {
	
	public Object newInstance() throws Exception;
	public void persist(Object value) throws Exception;

}
