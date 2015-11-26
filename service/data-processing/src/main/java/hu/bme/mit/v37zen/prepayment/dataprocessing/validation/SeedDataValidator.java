package hu.bme.mit.v37zen.prepayment.dataprocessing.validation;

import hu.bme.mit.v37zen.sm.messaging.SeedData;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings("unused")
public class SeedDataValidator implements Validator<SeedData>, ApplicationContextAware{
	
	private ApplicationContext applicationContext;
		
	private SeedData seedData;

	public SeedDataValidator(SeedData seedData) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;		
	}

	@Override
	public void setData(SeedData seedData) {
		this.seedData = seedData;
	}

}
