package hu.bme.mit.v37zen.prepayment.datasync.debug;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Debug {
	
	public final static Logger logger = LoggerFactory.getLogger(Debug.class);

	public static void main(String[] args) {
			
//		logger.debug("Debug");
//		logger.info("Info");
//		logger.warn("Warn");
//		logger.error("Error");

		@SuppressWarnings("resource")
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/super-context.xml");
		ctx.registerShutdownHook();
		
		
//		JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//		
//		
//		logger.debug( "[JDBC test:] " + template.queryForList("select * from test;").get(0).toString());
		
		
	}

}
	