package com.trsvax.tapestry.misc.pages.nvl;

import java.util.Collection;
import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.trsvax.tapestry.misc.interfaces.Person;
import com.trsvax.tapestry.misc.services.PeopleDAO;

public class NVLIndex {

	@Property
	private Collection<Person> people;
	@Property
	private Person person;
	
	@Inject
	private PeopleDAO dao;
	
	void onActivate() {
		people = dao.getPeople();
	}

}
