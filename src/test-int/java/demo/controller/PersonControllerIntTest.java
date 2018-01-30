package demo.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import demo.Application;
import demo.model.Person;

/**
 * Uses the restTemplate made available through @SpringBootTest to run full Integration tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,  webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonControllerIntTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testFindAll() throws Exception {
		ResponseEntity<List> response = restTemplate.getForEntity("/personcontroller/findAll", List.class);
		
		Assert.assertNotNull(response);
		Assert.assertEquals(3, response.getBody().size());
	}
	
	@Test
	public void testPerson() throws Exception {
		ResponseEntity<Person> response = restTemplate.getForEntity("/persons/1", Person.class);
		
		Assert.assertNotNull(response);
		Assert.assertEquals("Apple", response.getBody().getLastName());
	}
	
}
