package com.trsvax.tapestry.misc.interfaces;

import org.apache.tapestry5.beaneditor.NonVisual;

public interface Person {
	
	@NonVisual
	public String getKey();
	public void setKey(String key);
	
	public void setName(String name);
	public String getName();

}
