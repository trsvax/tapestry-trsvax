package com.trsvax.tapestry.misc.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupConstants;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.base.AbstractLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ClientBehaviorSupport;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Request;
import org.slf4j.Logger;

public class Link extends AbstractLink {
	
	@Parameter(defaultPrefix = BindingConstants.PROP)
	private Object page;
	
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String event;
	
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String zone;
	
	@Parameter
	private Object[] context;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
	private PageRenderLinkSource pageRenderLinkSource;
	
	@Inject
	private Logger logger;
	
	@Environmental
	private ClientBehaviorSupport clientBehaviorSupport;
	 	
	@Inject
	private Request request;
	
	void beginRender(MarkupWriter writer) {
	 	if (isDisabled()) return;
	 	
	 	Component pageComponent = null;
	 		 	
	 	if ( page == null ) {
	 		pageComponent = resources.getPage();
	 	} else if ( Component.class.isAssignableFrom(page.getClass())) {
	 		pageComponent = (Component) page;
	 	}
	 	
	 	if ( page != null && String.class.isAssignableFrom(page.getClass())) {
	 		if ( resources.isBound("event")) {
	 			//pageComponent = converStringtoComponent(page);
	 		} else {
	 			writeLink(writer, pageLink((String) page));
	 		}
	 	}
	 	
	 	if (  pageComponent != null ) {
	 		org.apache.tapestry5.Link link;
	 		if ( resources.isBound("event")) {
		 		link = eventLink(pageComponent);
		 	} else {
		 		link = pageLink(pageComponent);
		 	}
	 		writeLink(writer,link);
		 	if (zone != null)
		 	{
		 		// might was zonelink from zoneEvent
		 		org.apache.tapestry5.Link zoneLink = link;
		 		if (!request.isXHR())
		 			writer.getElement().forceAttributes(MarkupConstants.ONCLICK, MarkupConstants.WAIT_FOR_PAGE);
		 		
		 		clientBehaviorSupport.linkZone(getClientId(), zone, zoneLink);
		 	}
		 	
	 		return;
	 	}
	 	

	 	
	}
	
 	void afterRender(MarkupWriter writer) {
		if (isDisabled()) return;
		
		writer.end(); // <a>
 	}
	
	private org.apache.tapestry5.Link pageLink(String page) {
		if ( resources.isBound("context")) {
			return pageRenderLinkSource.createPageRenderLinkWithContext(page, context);
		} 
		return pageRenderLinkSource.createPageRenderLink(page);
	}
	
	private org.apache.tapestry5.Link pageLink(Component page) {
		if ( resources.isBound("context")) {
			return pageRenderLinkSource.createPageRenderLinkWithContext(page.getClass(), context);
		} 
		return pageRenderLinkSource.createPageRenderLink(page.getClass());
	}
	
	private org.apache.tapestry5.Link eventLink(Component page) {
		ComponentResources resources = page.getComponentResources();
		return resources.createEventLink(event, context);
	}


	

}
