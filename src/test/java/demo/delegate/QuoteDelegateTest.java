package demo.delegate;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.WireMockSpring;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

import demo.model.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="app.baseUrl=http://localhost:9999", webEnvironment=WebEnvironment.NONE)
@DirtiesContext
public class QuoteDelegateTest {

	@Autowired
	private QuoteDelegate quoteDelegate;

	@ClassRule
	public static WireMockClassRule wiremock = new WireMockClassRule(WireMockSpring.options().port(9999));

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	public void testRetrieveRandomQuote() throws Exception {
		
		String mockJsonResponse = getFileContents("demo/delegate/RandomQuoteResponse01.json");
		
		stubFor(get(urlEqualTo("/api/random"))
				.willReturn(aResponse()
						.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withBody(mockJsonResponse)));
		
		Quote q = this.quoteDelegate.retrieveRandomQuote();
		
		assertThat( q ).isNotNull();
		assertThat( q.getType() ).isEqualTo( "success" );
		assertThat( q.getValue().getId() ).isEqualTo( 10L );
		assertThat( q.getValue().getQuote() ).isEqualTo( "Really loving Spring Boot, makes stand alone Spring apps easy." );
	}
	
	private String getFileContents(String fileName) {

		StringBuilder result = new StringBuilder("");

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line); //.append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result.toString();

	  }
}
