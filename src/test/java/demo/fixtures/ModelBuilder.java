package demo.fixtures;

import demo.model.Person;

public class ModelBuilder {

	public static Person createPerson(long id, String firstName, String lastName) 
	{
		Person person = new Person();
		person.setId(id);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return person;
	}

	
}
