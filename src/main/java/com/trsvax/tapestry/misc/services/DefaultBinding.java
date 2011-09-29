package com.trsvax.tapestry.misc.services;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.internal.bindings.AbstractBinding;
import org.apache.tapestry5.ioc.Location;

public class DefaultBinding extends AbstractBinding {
	private final Defaults defaults;
	
	private final String description;
	private final String expression;
	private final ComponentResources component;
	private final ComponentResources container;
 
	public DefaultBinding(String description, ComponentResources container,
			ComponentResources component, String expression, Location location, Defaults defaults) {
		super(location);
		this.defaults = defaults;
		this.expression = expression;
		this.component = component;
		this.container = container;
		this.description = description;
	}

	public Object get() {
		return defaults.get(this);
	}

	public Defaults getDefaults() {
		return defaults;
	}

	public String getDescription() {
		return description;
	}

	public String getExpression() {
		return expression;
	}

	public ComponentResources getComponent() {
		return component;
	}

	public ComponentResources getContainer() {
		return container;
	}
	
}
