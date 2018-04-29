package hello;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingTest2 {
	
	@Autowired
	private MockMvc mvc;

	@Test
	public void getDefaultResponseGreeting() throws Exception {
		String expectedString = "{\"id\":2,\"content\":\"Hello, World\"}";
		
		mvc.perform(MockMvcRequestBuilders.get("/greeting").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(expectedString)));
	}
	
	@Test
	public void getNamedResponseGreeting() throws Exception {
		String expectedString = "{\"id\":1,\"content\":\"Hello, Maria\"}";
		
		mvc.perform(MockMvcRequestBuilders.get("/greeting?name=Maria").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(expectedString)));
	}
	
	@Test
	public void getDefaultResponseOtherGreeting() throws Exception {
		String expectedString =  "{\"id\":2,\"content\":\"Hello there Klara\"}";
		
		mvc.perform(MockMvcRequestBuilders.get("/other-greeting").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(expectedString)));
	}
	
	@Test
	public void getNamedResponseOtherGreeting() throws Exception {
		String expectedString =  "{\"id\":1,\"content\":\"Hello there Maria\"}";
		
		mvc.perform(MockMvcRequestBuilders.get("/other-greeting?other-name=Maria").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo(expectedString)));
	}

}
