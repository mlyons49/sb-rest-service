package demo.delegate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import demo.Application;
import demo.model.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,  webEnvironment = WebEnvironment.RANDOM_PORT)
public class QuoteDelegateIntTest {

	@Autowired
	private QuoteDelegate quoteDelegate;
	
	@Test
	public void testRetrieveRandomQuote() throws Exception {
		
		Quote q = quoteDelegate.retrieveRandomQuote();

		Assert.assertNotNull(q);
		Assert.assertEquals("success", q.getType());
	}
	
}
