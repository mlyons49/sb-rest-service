package demo.repository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import demo.fixtures.ModelBuilder;
import demo.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest         //Instanciates MockMvc
@AutoConfigureMockMvc   // MockMvc AutoConfiguration Eliminates need for Builder
@ActiveProfiles("unit.test")
public class PersonRepositoryIntTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MockMvc mockMvc;

    //Calls direct to PersonRepository with runtime db connection

    @Test
    public void testFindByLastName() {
    	
    	List<Person> l = personRepository.findByLastName("Cantelope");
    	
    	Assert.assertNotNull(l);
    	Assert.assertEquals("Carl", l.get(0).getFirstName());
    	Assert.assertEquals("Cantelope", l.get(0).getLastName());
    }
    
    @Test
    public void testFindByLastNameLike() {
    	
    	List<Person> l = personRepository.findByLastNameLike("A");
    	
    	Assert.assertNotNull(l);
    	Assert.assertEquals("Alice", l.get(0).getFirstName());
    	Assert.assertEquals("Apple", l.get(0).getLastName());
    }

    
    //Calls to PersonRepository via mockMvc with runtime db connection
    
    @Test
    public void testPersonsGet() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/persons"))
                .andExpect( status().isOk())
                .andReturn();
        String actualJson = mvcResult.getResponse().getContentAsString();
        Assert.assertTrue(actualJson.contains("Banana"));
//        JSONAssert.assertEquals(outJSON,mvcResult.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
    }

    @Test
    public void testPersonsPost() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/persons")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{ \"firstName\" : \"John\", \"lastName\" : \"Borys\" }"))
        .andExpect( status().is(201))
        .andDo(print())
        .andReturn();
        //String content = mvcResult.getResponse().getContentAsString();
    }


    
}
