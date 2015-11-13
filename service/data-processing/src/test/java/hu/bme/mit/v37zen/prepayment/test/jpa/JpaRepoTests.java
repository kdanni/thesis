package hu.bme.mit.v37zen.prepayment.test.jpa;

import hu.bme.mit.v37zen.sm.jpa.datamodel.Account;
import hu.bme.mit.v37zen.sm.jpa.datamodel.Parameter;
import hu.bme.mit.v37zen.sm.jpa.datamodel.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.jpa.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.IntervalReadingRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:/META-INF/spring/test-context.xml", "classpath:/META-INF/spring/jdbc-jpa.xml"})
public class JpaRepoTests {
	
	@Resource
	private Environment environment;

	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private ServiceDeliveryPointRepository sdpRepo;
	
	@Autowired
	private IntervalReadingRepository intervalRepo;
	
	@AfterClass
	public static void sleepFor5Min(){
		
		try {
			Thread.sleep(300000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void accRepoTest(){
		
		String mRID = "Acc01";
		String name = "Account 001";
		String status = "active";
		String type = "Customer";
		
		Account account = new Account(mRID);
		account.setName(name);
		account.setStatus(status);
		account.setAccountType(type);
		
		accRepo.saveAndFlush(account);
		
		Assert.assertEquals(account, accRepo.findByMRID(mRID).get(0));
		Assert.assertEquals(1, accRepo.findByMRID(mRID).size());
		
		Assert.assertTrue(accRepo.findByName(name).contains(account));
		
		Assert.assertTrue(accRepo.findByStatus(status).contains(account));
		
		Assert.assertTrue(accRepo.findByAccountType(type).contains(account));
		
		Assert.assertTrue(accRepo.findAll().contains(account));
		
	}
	
	@Test
	public void sdpRepoTest(){
		
		String mRID = "SDP01";
		String virtualInd = "sdp01-virtInd";
		String serviceType = "GASS";
		String premiseId = "premise01";
		Parameter parameter = new Parameter("param1", "value1", "active", new Date());
		
		
		ServiceDeliveryPoint sdp = new ServiceDeliveryPoint(mRID, virtualInd, serviceType, premiseId, parameter);	
		sdpRepo.saveAndFlush(sdp);
		
		Assert.assertEquals(sdp, sdpRepo.findByMRID(mRID).get(0));
		Assert.assertEquals(1, sdpRepo.findByMRID(mRID).size());
		
		Assert.assertTrue(sdpRepo.findByServiceType(serviceType).contains(sdp));
		
		Assert.assertTrue(sdpRepo.findByVirtualInd(virtualInd).contains(sdp));
		
		Assert.assertTrue(sdpRepo.findAll().contains(sdp));
		
	}
	
	@Test
	public void meterReadingTest(){
		
		//IBM-PB-MET-20
		String referenceId = "IBM-PB-MET-20";
		
		
		IntervalReading i = new IntervalReading(null,
				referenceId, IntervalReading.METER_X_UDC_ASSET_ID, "NAMSPACE", 
				"1-1:1.8.0.0", 326.45, true, false, null, 900, UUID.randomUUID().toString(), new Date());
		intervalRepo.saveAndFlush(i);
		
		//Assert.assertEquals(i, intervalRepo.findByMeterReferenceId(referenceId).get(0));
		Assert.assertEquals(1, intervalRepo.findByMeterReferenceId(referenceId).size());
		
		assert i.equals(intervalRepo.findByMeterReferenceId(referenceId).get(0));
		
	}	
}