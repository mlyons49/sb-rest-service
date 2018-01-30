package demo.repository;




////@RunWith(SpringRunner.class)
////@WebMvcTest(controllers = PersonRepository.class, secure=false)
////@SpringBootTest //webEnvironment = SpringBootTest.WebEnvironment.MOCK)
////@ContextConfiguration("PersonRepository")
////@RunWith(SpringJUnit4ClassRunner.class)
////@SpringApplicationConfiguration(classes = Application.class)
////@BootstrapWith(value=org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTestContextBootstrapper.class)
////@AutoConfigureWebMvc
////@AutoConfigureMockMvc


import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import demo.fixtures.ModelBuilder;
import demo.model.Person;

/**
 * Unit testing the PersonRepository, mocking out the db conneciton using @DataJpaTest and TestEntityManager
 * 
 * Note: There is a startup conflict when @EnableSwagger2 is used with the @SpringBootApplication (Swagger is disabled).
 * 
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindAll() {
    	entityManager.persist( ModelBuilder.createPerson(0, "JUnit", "Tester1"));
    	entityManager.persist( ModelBuilder.createPerson(0, "JUnit", "Tester2"));
    	
    	Iterable<Person> l = personRepository.findAll();
    	
    	Assert.assertNotNull(l);
    	Iterator<Person> iter = l.iterator();
    	Assert.assertEquals("Tester1", iter.next().getLastName());
    	Assert.assertEquals("Tester2", iter.next().getLastName());
    }
    
    @Test
    public void testFindByLastName() {
    	entityManager.persist( ModelBuilder.createPerson(0, "JUnit", "Tester"));
    	
    	List<Person> l = personRepository.findByLastName("Tester");
    	
    	Assert.assertNotNull(l);
    	Assert.assertNotNull("JUnit", l.iterator().next().getFirstName());
    }

    @Test
    public void testFindByLastNameLike() {
    	entityManager.persist( ModelBuilder.createPerson(0, "JUnit", "Tester"));
    	
    	List<Person> l = personRepository.findByLastNameLike("Tester");
    	
    	Assert.assertNotNull(l);
    	Assert.assertNotNull("JUnit", l.iterator().next().getFirstName());
    }

}
