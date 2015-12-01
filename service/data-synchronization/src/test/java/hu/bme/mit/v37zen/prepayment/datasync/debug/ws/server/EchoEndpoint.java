package hu.bme.mit.v37zen.prepayment.datasync.debug.ws.server;

import hu.bme.mit.v37zen.prepayment.datasync.debug.ws.WsDebugMain;

import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EchoEndpoint {

	public final static Logger logger = LoggerFactory.getLogger(WsDebugMain.class);
	
	@ResponsePayload
	@PayloadRoot(localPart="echoRequest", namespace="http://www.example.org/echo")
	public Element echo(@RequestPayload Element in){
		logger.debug("Echo called!", in);
		return in;
	}

}
