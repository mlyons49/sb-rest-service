package demo.delegate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import demo.model.Quote;

@Component
public class QuoteDelegate {

	@Value("${app.baseUrl:http://gturnquist-quoters.cfapps.io}")
	private String baseUrl;
	
	public demo.model.Quote retrieveRandomQuote() {
		RestTemplate restTemplate = new RestTemplate();
		Quote quote = restTemplate.getForObject(baseUrl + "/api/random", Quote.class);
	    return quote;
	}
	
}
