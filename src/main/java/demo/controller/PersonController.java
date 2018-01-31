package demo.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.exception.ClaimsBusinessException;
import demo.exception.ClaimsValidationException;
import demo.model.Person;
import demo.service.PersonService;

@RestController
public class PersonController {

    //@Autowired 		<-- not autowired to allow MockMvc testing
    private PersonService personService;
    
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

    @GetMapping("/personcontroller/findAll")
    public Iterable<Person> findAll(){
        return personService.findAll();
    }
    
    @GetMapping("/personcontroller/findByLastName")
    public List<Person> findByLastName(@RequestParam("lastName") String lastName){
    	return personService.findByLastName(lastName);
    }

    @GetMapping("/personcontroller/findByLastNameLike")
    public List<Person> findByLastNameLike(@RequestParam("lastName") String lastName){
    	return personService.findByLastNameLike(lastName);
    }

    @GetMapping("/personcontroller/testRuntimeException")
    public List<Person> testRuntimeException(@RequestParam("lastName") String lastName){
    	if (lastName.length() != 8) {
    		throw new RuntimeException("Whoops there was a RuntimeException!");
    	}
    	return personService.findByLastNameLike(lastName);
    }

    @GetMapping("/personcontroller/testClaimsBusinessException")
    public List<Person> testClaimsBusinessException(@RequestParam("lastName") String lastName) throws ClaimsBusinessException {
    	if (lastName.length() != 8) {
    		throw new ClaimsBusinessException("LN101", "The lastName must be exactly 8 chars long - cbx");
    	}
    	return personService.findByLastNameLike(lastName);
    }
    
    @GetMapping("/personcontroller/testClaimsValidationException")
    public List<Person> testClaimsValidationException(@RequestParam("lastName") String lastName) throws ClaimsValidationException {
    	if (lastName.length() != 8) {
    		throw new ClaimsValidationException("The lastName must be exactly 8 chars long - cvx");
    	}
    	return personService.findByLastNameLike(lastName);
    }
    
    @PutMapping("/personcontroller/create")
    public Person create( @Valid @RequestBody Person person) {
    	// do stuff
    	return person;
    }
    
}
