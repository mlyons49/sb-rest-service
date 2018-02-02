package demo;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import demo.model.Person;
import demo.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseInitializerTest {

    @Mock
    private PersonRepository personRepository;

	@InjectMocks
	private DatabaseInitializer databaseInitializer;

	@Test
	public void testInitTestData() throws Exception {

		when(personRepository.save(isA(Person.class))).thenReturn( new Person(), new Person(), new Person() );
		
		databaseInitializer.initTestData();
		
		//verify(personRepository, times(3)).save(isA(Person.class));
		
		
	}
}
