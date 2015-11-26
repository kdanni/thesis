package hu.bme.mit.v37zen.prepayment.dataprocessing;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.SeedDataValidator;
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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class Preprocessor implements ApplicationContextAware {
	
	private static Logger logger = LoggerFactory.getLogger(Preprocessor.class);
	
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	private ApplicationContext applicationContext;
	
	public Preprocessor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}
	
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
		
		SeedDataValidator seedDataValidator = applicationContext.getBean(SeedDataValidator.class);
		seedDataValidator.setData(seedData);
		
		this.threadPoolTaskExecutor.execute(seedDataValidator);
	}
	
	public void meterReading(IntervalReading intervalReading){
		
		logger.debug("IntervalReading" + intervalReading.toString());
	}

	public void paymentData(Payment payment){
	
		logger.debug("Payment: \n" + payment.toString());
	}

	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
		return threadPoolTaskExecutor;
	}

	public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
