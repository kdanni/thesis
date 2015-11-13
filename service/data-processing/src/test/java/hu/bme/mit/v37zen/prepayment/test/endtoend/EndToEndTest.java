package hu.bme.mit.v37zen.prepayment.test.endtoend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/super-context.xml")
public class EndToEndTest {

	@Test
	public void main() throws InterruptedException{
		
		
		Thread.sleep(300000);
	}
}
