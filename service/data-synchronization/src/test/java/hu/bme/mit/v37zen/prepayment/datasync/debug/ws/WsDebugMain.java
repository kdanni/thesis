package hu.bme.mit.v37zen.prepayment.datasync.debug.ws;

import hu.bme.mit.v37zen.prepayment.application.datasync.DataSyncApplication;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

public class WsDebugMain {
	
	public final static Logger logger = LoggerFactory.getLogger(DataSyncApplication.class);
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
		
		ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);		
        
        
        
        
        server.start();
        server.join();
        
        
	}

}
