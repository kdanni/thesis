package hu.bme.mit.v37zen.prepayment.dataprocessing;

import java.io.Serializable;

import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.messaging.DataProcessRequest;
import hu.bme.mit.v37zen.sm.messaging.SeedData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Preprocessor {
	
	private static Logger logger = LoggerFactory.getLogger(Preprocessor.class);
	
	public void dataProcessRequests(DataProcessRequest<?> messageBody){
		
		logger.debug("Echo: \n" + messageBody.toString());
		
		Serializable entity = messageBody.getEntity();
		
		if(entity instanceof IntervalReading){
			this.meterReading((IntervalReading)messageBody.getEntity());
		}
		if(entity instanceof Payment){
			this.paymentData((Payment)messageBody.getEntity());
		}		
		if(entity instanceof SeedData){
			this.seedData((SeedData)entity);
		}
	}	
	
	public void seedData(SeedData seedData){
		
		logger.debug("SeedData: \n" + seedData.toString());
	}
	
	public void meterReading(IntervalReading intervalReading){
		
		logger.debug("IntervalReading" + intervalReading.toString());
	}

	public void paymentData(Payment payment){
	
		logger.debug("Payment: \n" + payment.toString());
	}
}
