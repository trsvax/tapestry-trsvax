package com.trsvax.tapestry.misc.services;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.trsvax.tapestry.misc.interfaces.Person;

public class PeopleDAO {
	
	private final Map<String,Person> people = new HashMap<String, Person>();
	
	public Collection<Person> getPeople() {
		return people.values();
	}

	public void save(Person person) {
		if ( person.getKey() == null ) {
			person.setKey(""+new Date().getTime());
		}
		people.put(person.getKey(),person);
	}

	public Person get(String key) {
		return people.get(key);
	}
}
