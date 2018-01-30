package demo.service;

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
import demo.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testFindAll() throws Exception {
        List<Person> mockPersons = new ArrayList<Person>();
        mockPersons.add( createPerson(0, "JUnit1",  "Tester1") );
        mockPersons.add( createPerson(0, "JUnit2",  "Tester2") );

        when(personService.findAll()).thenReturn(mockPersons);

        Iterable<Person> actualPersons = personRepository.findAll();

        assertNotNull(actualPersons);
        
        Iterator<Person> iter = actualPersons.iterator();
        Person firstPerson = iter.next();
        assertEquals("JUnit1", firstPerson.getFirstName());
        
        Person secondPerson = iter.next();
        assertEquals("Tester2", secondPerson.getLastName());
    }	
    
    @Test
    public void testFindByLastName() throws Exception {

        List<Person> mockPersons = new ArrayList<Person>();
        mockPersons.add( createPerson(0, "JUnit1",  "Tester1") );
        mockPersons.add( createPerson(0, "JUnit2",  "Tester2") );
        when(personService.findByLastName(Mockito.isA(String.class))).thenReturn( mockPersons );

        List<Person> actualPersons = personRepository.findByLastName("Tester");

        assertNotNull(actualPersons);
        
        assertEquals("JUnit1", actualPersons.get(0).getFirstName());
        assertEquals("Tester1", actualPersons.get(0).getLastName());
    }
}
