package demo.controller;

import static demo.fixtures.ModelBuilder.createPerson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import demo.model.Person;
import demo.service.PersonService;

/**
 * Mockito based unit tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Test
    public void testFindAll() throws Exception {
        List<Person> mockPersons = createMockPersonsList();

        when(personService.findAll()).thenReturn(mockPersons);

        Iterable<Person> actualPersons = personController.findAll();

        assertNotNull(actualPersons);
        
        Iterator<Person> iter = actualPersons.iterator();
        Person firstPerson = iter.next();
        assertEquals("JUnit1", firstPerson.getFirstName());
        
        Person secondPerson = iter.next();
        assertEquals("Tester2", secondPerson.getLastName());
    }

	private List<Person> createMockPersonsList() {
		List<Person> mockPersons = new ArrayList<Person>();
        mockPersons.add( createPerson(0, "JUnit1",  "Tester1") );
        mockPersons.add( createPerson(1, "JUnit2",  "Tester2") );
		return mockPersons;
	}

    @Test
    public void testFindByLastName() throws Exception {
        List<Person> mockPersons = createMockPersonsList();

        when(personService.findByLastName(Mockito.isA(String.class))).thenReturn(mockPersons);

        List<Person> actualPersons = personController.findByLastName("Tester1");

        assertNotNull(actualPersons);
        
        Iterator<Person> iter = actualPersons.iterator();
        Person firstPerson = iter.next();
        assertEquals("JUnit1", firstPerson.getFirstName());
        assertEquals("Tester1", firstPerson.getLastName());
    }
    
    @Test
    public void testFindByLastNameLike() throws Exception {
        List<Person> mockPersons = createMockPersonsList();

        when(personService.findByLastNameLike(Mockito.isA(String.class))).thenReturn(mockPersons);

        List<Person> actualPersons = personController.findByLastNameLike("T");

        assertNotNull(actualPersons);
        
        Iterator<Person> iter = actualPersons.iterator();
        Person firstPerson = iter.next();
        assertEquals("JUnit1", firstPerson.getFirstName());
        assertEquals("Tester1", firstPerson.getLastName());
    }
}
