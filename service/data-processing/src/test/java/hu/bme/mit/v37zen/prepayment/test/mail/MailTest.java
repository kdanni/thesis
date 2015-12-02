package hu.bme.mit.v37zen.prepayment.test.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/test-context.xml")
public class MailTest {

	@Autowired
	ApplicationContext ctx;
	
	@Test
	public void main() throws InterruptedException{
		
		
		try {
			JavaMailSenderImpl ms = new JavaMailSenderImpl();
			ms.setHost("127.0.0.1");
			ms.setUsername("prepay@127.0.0.1");
			ms.setPassword("password");
			ms.setPort(25);
			
			SimpleMailMessage smm = new SimpleMailMessage();
			smm.setText("Hali!");
			smm.setTo("testuser@127.0.0.1");
			smm.setSubject("PRPAYMENT");
			smm.setFrom("prepay@127.0.0.1");
			
			
			ms.send(smm);
		
		
		} catch (MailException e) {
			e.printStackTrace();
		}
		
		
	}
}
