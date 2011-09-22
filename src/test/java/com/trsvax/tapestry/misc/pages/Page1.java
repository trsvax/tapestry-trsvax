package com.trsvax.tapestry.misc.pages;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

public class Page1 {
	
	@Property
	@Persist
	private String init;
	
	void init(String value) {
		init = value;
	}
	
	Object onMyEvent(String value) {
		return Page1.class;
	}

}
