package com.trsvax.tapestry.misc.pages.nvl;

import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import com.trsvax.tapestry.misc.annotations.NVL;
import com.trsvax.tapestry.misc.interfaces.Person;
import com.trsvax.tapestry.misc.services.PeopleDAO;

public class NVLEdit {
	
	@PageActivationContext
	@Property
	@NVL
	private Person person;
	
	@Inject
	private Logger logger;
	
	@Inject
	private PeopleDAO dao;
	
	Object onSuccess() {
		logger.info("name {}",person.getName());
		dao.save(person);
		return NVLIndex.class;
	}

}
