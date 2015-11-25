package hu.bme.mit.v37zen.prepayment.test.endtoend;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/super-context.xml")
public class DataprocessEndToEndTest {

	@Autowired JmsTemplate jmsTemplate;
	
	@Test
	public void main() throws InterruptedException{
		
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				AccountSDPAssociation asa = new AccountSDPAssociation();
				asa.setAccountMRID("30230323-sdgsdfg-01231242");
				asa.setSdpMRID("sadfasf-fadfasf-adfaddfa");
				return session.createObjectMessage(asa);
			}
		});		
		
		Thread.sleep(300000);
	}
}
