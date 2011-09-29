package com.trsvax.tapestry.misc.services;

import java.util.Collection;

public interface StaticDefaults {
	
	public String get(String key);
	public Collection<String> keys();

}
