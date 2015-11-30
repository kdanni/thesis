package hu.bme.mit.v37zen.prepayment.test.jpa;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.Balance;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.jpa.repositories.BalanceRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PaymentRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentAccountRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/super-context.xml")
public class LazyRpositoryTest {

	@Autowired JmsTemplate jmsTemplate;
	
	@Autowired BalanceRepository balanceRepository;
	
	@Autowired PrepaymentAccountRepository prepaymentAccountRepository;
	
	@Autowired PaymentRepository paymentRepository;
	
	@Test
	public void main() throws InterruptedException{
		
//		jmsTemplate.send(new MessageCreator() {
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				AccountSDPAssociation asa = new AccountSDPAssociation();
//				asa.setAccountMRID("30230323-sdgsdfg-01231242");
//				asa.setSdpMRID("sadfasf-fadfasf-adfaddfa");
//				return session.createObjectMessage(asa);
//			}
//		});		
		
		PrepaymentAccount pa = new PrepaymentAccount();
		pa.setMRID("ACC-id");
		pa.setAccountMRID("ACC-id");
		
		List<Balance> bl = new ArrayList<Balance>();
		
		bl.add(new Balance(5, new Date()));
		Thread.sleep(4000);
		bl.add(new Balance(6, new Date()));
		Thread.sleep(4000);
		bl.add(new Balance(7, new Date()));
		
		for (Balance balance : bl) {
			balance.setPrepaymentAccountMRID("ACC-id");
			pa.getBalance().add(balance);
		}
		
		Payment p = new Payment(2203, "HIF", new Date());
		p = paymentRepository.save(p);
		
		pa.getPayments().add(p);
		
		balanceRepository.save(bl);
		balanceRepository.flush();
		prepaymentAccountRepository.save(pa);
		prepaymentAccountRepository.flush();
		
		//List<Balance> result = balanceRepository.findByAccountId("ACC-id");

//		List<Balance> result = balanceRepository.findByAccountIdOrderByDateDesc("ACC-id", new PageRequest(0, 1));
//
//		System.out.println(result.size());
//		for (Balance balance : result) {
//			System.out.println(balance.toString());
//		}

		
		PrepaymentAccount pacc = prepaymentAccountRepository.findByIdFetchMeterReading(1L);
		System.out.println("PPaCC: " + pacc);
		
		Thread.sleep(300000);
	}
}
