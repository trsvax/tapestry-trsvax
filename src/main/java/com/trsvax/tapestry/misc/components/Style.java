package com.trsvax.tapestry.misc.components;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Environmental;

import com.trsvax.tapestry.StyleSupport;

public class Style {
	
	@Environmental(false)
	private StyleSupport styleSupport;
	
	void beginRender(MarkupWriter writer) {
		
		if ( styleSupport == null ) return;
		StringBuffer output = new StringBuffer();
		output.append("\n");
		for ( Entry<String, Map<String, String>> style : styleSupport.style().entrySet() ) {
			String name = style.getKey();
			Map<String,String> items = style.getValue();
						
			output.append(name);
			output.append(" {");
			for ( Entry<String,String> item : items.entrySet() ) {
				output.append(item.getKey());
				output.append(":");
				output.append(item.getValue());
				output.append(";");
			}
			
			output.append("}\n");
		}
		writer.element("style", "type","text/css");
		writer.writeRaw(output.toString());
		writer.end();
		
	}
	

}
