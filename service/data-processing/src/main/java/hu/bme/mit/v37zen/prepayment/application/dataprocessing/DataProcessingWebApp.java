package hu.bme.mit.v37zen.prepayment.application.dataprocessing;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataProcessingWebApp {

	public final static Logger logger = LoggerFactory.getLogger(DataProcessingWebApp.class);
	public final static String DEFAULT_WAR_FILE = "src/main/resource/";
	
	
	public static void main(String[] args) {
		try{
			String warFilePath = DEFAULT_WAR_FILE;
			if(args.length > 0){		
				warFilePath = args[0];
			}			
			
			Server server = new Server(8081);
			
			WebAppContext webapp = new WebAppContext();
		    webapp.setContextPath("/");
		    File warFile = new File(warFilePath);
		    webapp.setWar(warFile.getAbsolutePath());
		    server.setHandler(webapp);        
		    server.start();
		    server.join();
		}
		catch (Exception e) {
			logger.error("Usage: DataSyncApplication [config_location..]");
			logger.error(e.getMessage(), e);
		}
	}	

}
