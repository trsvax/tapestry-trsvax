package com.trsvax.tapestry.misc.components;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Parameter;

import com.trsvax.tapestry.misc.services.MiscDefaults;

public class WithDefault {
	
	@Parameter(MiscDefaults.defaultKey)
	private String value;
	
	void beginRender(MarkupWriter writer) {
		writer.write(value);
	}

}
