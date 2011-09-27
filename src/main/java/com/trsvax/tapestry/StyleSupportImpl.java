package com.trsvax.tapestry;

import java.util.LinkedHashMap;
import java.util.Map;

public class StyleSupportImpl implements StyleSupport {
	
	Map<String,Map<String,String>> styles = new LinkedHashMap<String, Map<String,String>>();

	public Map<String, Map<String, String>> style() {
		return styles;
	}

}
