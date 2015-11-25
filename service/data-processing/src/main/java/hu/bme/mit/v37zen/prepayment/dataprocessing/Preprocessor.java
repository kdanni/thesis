package hu.bme.mit.v37zen.prepayment.dataprocessing;

import hu.bme.mit.v37zen.sm.messaging.DataProcessRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Preprocessor {
	
	private static Logger logger = LoggerFactory.getLogger(Preprocessor.class);
	
	public void process(DataProcessRequest<?> messageBody){
		
		logger.debug("Echo: \n" + messageBody.toString());
		
	}
}
