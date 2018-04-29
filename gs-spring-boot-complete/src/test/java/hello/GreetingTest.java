package hello;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingTest {

	@LocalServerPort
	private int port;
	
	private URL base;
	
	@Autowired
	private TestRestTemplate template;
	
	@Test
	public void testDefault() throws MalformedURLException {
		String expectedString = "{\"id\":2,\"content\":\"Hello, World\"}";
		this.base = new URL("http://localhost:" + port + "/greeting");
		
		ResponseEntity<String> response = template.getForEntity(
				base.toString(), String.class);
		
		String actualString = response.getBody();
		
		assertThat(actualString, equalTo(expectedString));
	}
	
	@Test
	public void testName() throws MalformedURLException {
		String expectedString = "{\"id\":1,\"content\":\"Hello, Maria\"}";
		this.base = new URL("http://localhost:" + port + "/greeting?name=Maria");
		
		ResponseEntity<String> response = template.getForEntity(
				base.toString(), String.class);
	
		String actualString = response.getBody();
		
		assertThat(actualString, equalTo(expectedString));
	}

	
}
