package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	private static final String template = "Hello, %s";
	private final AtomicLong counter = new AtomicLong();
	
	private static final String otherTemplate = "Hello there %s";
	private final AtomicLong otherCounter = new AtomicLong();
    
	@RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	return new Greeting(counter.incrementAndGet(),
    			String.format(template, name));
    }
	
	
	@RequestMapping("/other-greeting")
	public Greeting othergreeting(@RequestParam(value="other-name", defaultValue="Klara") String name) {
		return new Greeting(otherCounter.incrementAndGet(),
				String.format(otherTemplate, name));
	}
}
