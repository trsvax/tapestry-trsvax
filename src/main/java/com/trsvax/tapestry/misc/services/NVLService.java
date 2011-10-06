package com.trsvax.tapestry.misc.services;

public interface NVLService {
	
	public boolean isImplemented(String type);
	public Object newInstance(String type);
	public void persist(String type, Object object);

}
