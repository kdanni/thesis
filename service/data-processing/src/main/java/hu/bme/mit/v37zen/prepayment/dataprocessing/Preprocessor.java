package hu.bme.mit.v37zen.prepayment.dataprocessing;

import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.messaging.DataProcessRequest;
import hu.bme.mit.v37zen.sm.messaging.SeedData;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

public class Preprocessor implements ApplicationContextAware {
	
	private static Logger logger = LoggerFactory.getLogger(Preprocessor.class);
	
	protected ApplicationContext applicationContext;
	
	protected MessageChannel routerChannel; 

	public Preprocessor(MessageChannel routerChannel) {
		this.routerChannel = routerChannel;
	}
	
	public void dataProcessRequests(DataProcessRequest<?> messageBody){
		logger.debug("Preprocessing..");
		Serializable entity = messageBody.getEntity();
		if(entity instanceof IntervalReading){
			routerChannel.send(new GenericMessage<IntervalReading>((IntervalReading)messageBody.getEntity()));
		}
		if(entity instanceof Payment){
			routerChannel.send(new GenericMessage<Payment>((Payment)messageBody.getEntity()));
		}		
		if(entity instanceof SeedData){
			routerChannel.send(new GenericMessage<SeedData>((SeedData)messageBody.getEntity()));
		}
	}	
	
	public SeedData seedData(SeedData seedData){
		logger.debug("SeedData: \n" + seedData.toString());
		
		return seedData;
	}
	
	public IntervalReading meterReading(IntervalReading intervalReading){
		logger.debug("IntervalReading: \n" + intervalReading.toString());
		return intervalReading;
	}

	public Payment paymentData(Payment payment){
		logger.debug("Payment: \n" + payment.toString());
		return payment;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
