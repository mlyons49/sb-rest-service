package demo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import demo.fixtures.ModelBuilder;

public class PersonTest {

	@Test
	public void testPerson() throws Exception {
		Person person = ModelBuilder.createPerson(99L, "Bob", "Banana");
        assertEquals(99L, person.getId());
        assertEquals("Bob", person.getFirstName());
		assertEquals("Banana" ,person.getLastName());
	}


}


