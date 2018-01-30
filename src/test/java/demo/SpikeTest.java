package demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import demo.delegate.QuoteDelegate;
import demo.model.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,  webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpikeTest {

	@Autowired
	private QuoteDelegate quoteDelegate;
	
	@Test
	public void testWireMock() throws Exception {
		Quote q = quoteDelegate.retrieveRandomQuote();
		Assert.assertNotNull(q);
	}
}
