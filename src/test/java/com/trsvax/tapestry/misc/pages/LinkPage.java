package com.trsvax.tapestry.misc.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

public class LinkPage {
	@Property
	@InjectPage
	private Page1 page1;
	
	@Property
	@InjectPage
	private Page1 page1init;
	
	@InjectComponent
	 private Zone myZone;
	
	@Inject
	private Logger logger;
	
	void onActivate() {
		page1init.init("test");
	}
	
	void onAboutEvent(String value) {
		logger.info("about event {}",value);
	}
	
	Object onZoneEvent(String value) {
		return myZone;
	}
	


}
