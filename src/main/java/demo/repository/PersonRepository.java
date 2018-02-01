package demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import demo.model.Person;


//curl -i -X POST -H "Content-Type:application/json" -d '{ "firstName" : "John", "lastName" : "Borys" }' localhost:8080/persons

@Transactional
@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {

	public List<Person> findByLastName(String lastName);

	@Query("select p from Person p where p.lastName like %?1%")
	public List<Person> findByLastNameLike(String lastName);
}
