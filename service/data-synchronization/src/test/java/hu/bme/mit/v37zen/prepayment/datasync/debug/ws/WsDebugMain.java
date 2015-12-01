package hu.bme.mit.v37zen.prepayment.datasync.debug.ws;

import hu.bme.mit.v37zen.prepayment.datasync.debug.ws.server.MeterReadEndpointDebug;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;

@EnableWs
@Configuration
@ComponentScan(basePackageClasses={MeterReadEndpointDebug.class})
public class WsDebugMain {
	
	public final static Logger logger = LoggerFactory.getLogger(WsDebugMain.class);
	public final static String DEFAULT_CONTEXT = "classpath:/META-INF/spring/super-context.xml";

	
	public static void main(String[] args) throws Exception {

//		AbstractApplicationContext ctx = null;
//		try{
//			if(args.length == 0){		
//				ctx = new ClassPathXmlApplicationContext(DEFAULT_CONTEXT);
//			} else {
//				ctx = new FileSystemXmlApplicationContext(args);
//			}			
//		}
//		catch (Exception e) {
//			logger.error("Usage: DataSyncApplication [config_location..]");
//			logger.error(e.getMessage(), e);
//		}
//		ctx.registerShutdownHook();
		
		Server server = new Server(8080);
		
		WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        File warFile = new File(
                "src/main/webapp");
        webapp.setWar(warFile.getAbsolutePath());
        server.setHandler(webapp);        
        server.start();
        server.join();
        
        
	}

}
