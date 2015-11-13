package hu.bme.mit.v37zen.prepayment.datasync.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugTask implements Runnable {
	
	public static Logger logger = LoggerFactory.getLogger(DebugTask.class);
	
	private String message;

	public DebugTask(String message) {
		this.message = message;
	}

	public void run() {
		
		for (int i = 1; i < 6; i++) {
			logger.info(message + "." + i);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			}
		}
	}

}
