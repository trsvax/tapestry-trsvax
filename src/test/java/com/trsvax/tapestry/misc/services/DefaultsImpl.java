package com.trsvax.tapestry.misc.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.ioc.services.SymbolSource;

public class DefaultsImpl implements Defaults {
	private final Map<String,StaticDefaults> contributions;
	private final SymbolSource symbolSource;

	public DefaultsImpl(Map<String,StaticDefaults> contributions, SymbolSource symbolSource) {
		this.contributions = contributions;
		this.symbolSource = symbolSource;
	}

	public String get(DefaultBinding defaultBinding) {
		String[] parts = defaultBinding.getExpression().split(":");
		String id = parts[0];
		String key = parts[1];
		
		try {
			return symbolSource.valueForSymbol("default:" + defaultBinding.getExpression());
		} catch (Exception e) {
			//don't care
		}
		
		return contributions.get(id).get(key);
	}

	public Collection<String> keys() {
		List<String> keys = new ArrayList<String>();
		for ( StaticDefaults defaults : contributions.values() ) {
			keys.addAll(defaults.keys());
		}
		return keys;
	}
	
}
