package com.trsvax.tapestry.misc.implementations;

import com.trsvax.tapestry.misc.interfaces.Person;

public class PersonImpl implements Person {
	private String key;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
