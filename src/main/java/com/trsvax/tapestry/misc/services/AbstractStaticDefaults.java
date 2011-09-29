package com.trsvax.tapestry.misc.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractStaticDefaults implements StaticDefaults {

	public String get(String key) {
		try {
			return ((String) this.getClass().getField(key).get(this)).split(":")[3];
		} catch (Exception e) {
			
		}
		return null;
	}

	public Collection<String> keys() {
		Collection<String> keys = new ArrayList<String>();
		for ( Field field : this.getClass().getFields() ) {
			keys.add(field.getName());
		}
		return keys;
	}
}
