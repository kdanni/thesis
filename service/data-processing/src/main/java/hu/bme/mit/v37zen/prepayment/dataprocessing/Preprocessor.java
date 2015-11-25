package hu.bme.mit.v37zen.prepayment.dataprocessing;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Preprocessor {
	
	private static Logger logger = LoggerFactory.getLogger(Preprocessor.class);
	
	public void process(Account messageBody){
		
		logger.debug("Echo: \n" + messageBody.toString());
		
	}
}
