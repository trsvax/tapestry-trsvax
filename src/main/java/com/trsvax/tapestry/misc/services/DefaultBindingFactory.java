package com.trsvax.tapestry.misc.services;

import org.apache.tapestry5.Binding;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ioc.Location;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.slf4j.Logger;

/**
 * @since 2.6.0
 */
public class DefaultBindingFactory implements BindingFactory {
	private final Defaults defaults;
	
	public DefaultBindingFactory(Defaults defaults) {
		this.defaults = defaults;
	}

	public Binding newBinding(String description, ComponentResources container, ComponentResources component,
		                              String expression, Location location) {
		
		return new DefaultBinding(description,container,component,expression,location,defaults);
	}

}
