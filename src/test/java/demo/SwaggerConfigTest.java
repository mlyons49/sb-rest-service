package demo;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfigTest {

	private SwaggerConfig swaggerConfig;
	
	@Before
	public void setup() {
		swaggerConfig = new SwaggerConfig();
	}
	
	@Test
	public void testApi() throws Exception {
		Docket docket = swaggerConfig.api();
		assertNotNull(docket);
	}
}
