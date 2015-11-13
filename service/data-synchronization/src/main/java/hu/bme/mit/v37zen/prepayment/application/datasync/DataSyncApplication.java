package hu.bme.mit.v37zen.prepayment.application.datasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DataSyncApplication {
		
	public final static Logger logger = LoggerFactory.getLogger(DataSyncApplication.class);
	public final static String DEFAULT_CONTEXT = "classpath:/META-INF/spring/super-context.xml";
	
	public static void main(String[] args) {
		AbstractApplicationContext ctx = null;
		try{
			if(args.length == 0){		
				ctx = new ClassPathXmlApplicationContext(DEFAULT_CONTEXT);
			} else {
				ctx = new FileSystemXmlApplicationContext(args);
			}			
		}
		catch (Exception e) {
			logger.error("Usage: DataSyncApplication [config_location..]");
			logger.error(e.getMessage(), e);
		}
		ctx.registerShutdownHook();
	}

}
