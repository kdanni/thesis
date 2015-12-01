package hu.bme.mit.v37zen.prepayment.datasync.debug.ws;

import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/test-context.xml")
public class TestClient {
	
	private static final String MESSAGE = "<echoRequest xmlns='http://www.example.org/echo'>" +
			"Teszt" + "</echoRequest>";
	private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		
	@Test
	public void test(){
				
		try {
			StreamSource source = new StreamSource(new StringReader(MESSAGE));
			StreamResult result = new StreamResult(System.out);
			webServiceTemplate.sendSourceAndReceiveToResult("http://localhost:8080/prepay/echo",
			        source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}			 
		
	}
	
}
