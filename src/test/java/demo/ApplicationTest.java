package demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SpringApplication.class)
public class ApplicationTest {

	@Mock
	private ConfigurableApplicationContext ctx;

	@Test
	public void testApplicationConstructor() throws Exception {
		Application application = new Application();
		Assert.assertNotNull(application);
	}
	
	@Test
	public void testMain() throws Exception {
		
		//given
		String[] args = {"foo", "bar"};
		PowerMockito.mockStatic(SpringApplication.class);
        BDDMockito.given(SpringApplication.run(Mockito.isA(Class.class), Mockito.any(String[].class))).willReturn(ctx);

        //when
		Application.main(args);
		
		//then
		PowerMockito.verifyStatic(SpringApplication.class);
	}
}
