package hu.bme.mit.v37zen.prepayment.datasync.debug.ws.server;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Endpoint
public class MeterReadEndpointDebug {

	public final static Logger logger = LoggerFactory.getLogger(MeterReadEndpointDebug.class);
	
	@PayloadRoot(localPart="MeterDataMessage", namespace="http://www.emeter.com/energyip/meterdatainterface")
	public void meterreadings(@RequestPayload Element in){
		logger.debug("meterreadings ws called\n" + in.toString());
		logger.debug(new XMLOutputter().outputString(in));
	}

}
