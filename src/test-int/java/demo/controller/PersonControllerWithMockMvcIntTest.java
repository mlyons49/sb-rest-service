package demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

/**
 * Uses the mockMvc made available through @SpringBootTest to run full Integration tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest         
@AutoConfigureMockMvc   // MockMvc AutoConfiguration - eliminates need for builders
@ActiveProfiles("unit.test")
public class PersonControllerWithMockMvcIntTest {

    @Autowired
    private MockMvc mockMvc;

	@Test
	public void testFindAll() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/personcontroller/findAll"))
                .andExpect( status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$._embedded",org.hamcrest.Matchers.is("{\"persons\"") ))
                .andReturn();
        String actualJson = mvcResult.getResponse().getContentAsString();

        Assert.assertTrue(actualJson.contains("Apple"));
        Assert.assertTrue(actualJson.contains("Banana"));
		
	}
	
	@Test
	public void testPerson() throws Exception {
		 MvcResult mvcResult = mockMvc.perform(get("/persons/1"))
		         .andExpect( status().isOk())
		         //.andExpect(MockMvcResultMatchers.jsonPath("$._embedded",org.hamcrest.Matchers.is("{\"persons\"") ))
		         .andReturn();
		
	        String actualJson = mvcResult.getResponse().getContentAsString();
	        
	        Assert.assertTrue(actualJson.contains("Apple"));
	}
    

    @Test
    public void testPersonsPost() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/persons")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content("{ \"firstName\" : \"John\", \"lastName\" : \"Borys\" }"))
        .andExpect( status().is(201))
        .andDo(print())
        .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
    }


}
