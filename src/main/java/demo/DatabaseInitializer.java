package demo;

import demo.model.Person;
import demo.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    private PersonRepository personRepository;

    @PostConstruct
    public void initTestData() {

    	personRepository.save( createPerson("Alice", "Apple") );
        personRepository.save( createPerson("Bob", "Banana") );
        personRepository.save( createPerson("Carl", "Cantelope") );

    }

	private Person createPerson(String firstName, String lastName) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		return person;
	}

}