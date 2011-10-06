package com.trsvax.tapestry.misc.services;

import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.model.MutableComponentModel;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.runtime.ComponentEvent;
import org.apache.tapestry5.services.ClassTransformation;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.ComponentEventHandler;
import org.apache.tapestry5.services.TransformField;

public class ParameterInterfaceWorker  implements ComponentClassTransformWorker {
	private final InterfaceImplementation interfaceImplementation;
	
	public ParameterInterfaceWorker(InterfaceImplementation interfaceImplementation) {
		this.interfaceImplementation = interfaceImplementation;
	}

	public void transform(ClassTransformation transformation, MutableComponentModel model) {
		List<TransformField> fields = transformation.matchFieldsWithAnnotation(Parameter.class);
		for ( TransformField field : fields ) {
			if ( interfaceImplementation.isImplemented(field.getName())) {
				transformation.addComponentEventHandler(EventConstants.PREPARE, 0, "Parameter is Interface", handle(field));
			}			
		}		
	}
	
	private ComponentEventHandler handle(final TransformField field) {
		return new  ComponentEventHandler() {
			
			public void handleEvent(Component component, ComponentEvent event) {
				if ( field.getAccess().read(component) == null ) {
					field.getAccess().write(component, interfaceImplementation.newInstance(field.getName()));
				}
			}
		};
		
	}

}
