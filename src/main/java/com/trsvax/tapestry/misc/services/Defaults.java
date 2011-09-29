package com.trsvax.tapestry.misc.services;

import java.util.Collection;

public interface Defaults  {
	
	public String get(DefaultBinding defaultBinding);
	public Collection<String> keys();

}
