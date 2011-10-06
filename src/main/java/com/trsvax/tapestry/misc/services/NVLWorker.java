package com.trsvax.tapestry.misc.services;

import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.runtime.ComponentEvent;
import org.apache.tapestry5.services.ClassTransformation;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.ComponentEventHandler;
import org.apache.tapestry5.services.FieldAccess;
import org.apache.tapestry5.services.TransformField;

import com.trsvax.tapestry.misc.annotations.NVL;

public class NVLWorker  implements ComponentClassTransformWorker {
	private final NVLService interfaceImplementation;
	
	public NVLWorker(NVLService interfaceImplementation) {
		this.interfaceImplementation = interfaceImplementation;
	}

	public void transform(ClassTransformation transformation, MutableComponentModel model) {
		List<TransformField> fields = transformation.matchFieldsWithAnnotation(NVL.class);
		for ( TransformField field : fields ) {
			if ( interfaceImplementation.isImplemented(field.getType())) {
				transformation.addComponentEventHandler(EventConstants.ACTIVATE, 0, "Parameter has default", handle(field.getAccess(),field.getType()));
			}			
		}		
	}
	
	private  ComponentEventHandler handle(final FieldAccess access, final String type) {
		return new  ComponentEventHandler() {
			
			public void handleEvent(Component instance, ComponentEvent event) {
				if ( access.read(instance) == null ) {
					Object value = interfaceImplementation.newInstance(type);
					access.write(instance, value);
				}
			}
		};
		
	}

}
