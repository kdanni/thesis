package hu.bme.mit.v37zen.prepayment.webapi.endpoints;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloEndpoint {

	public final static Logger logger = LoggerFactory.getLogger(HelloEndpoint.class);

	
	@PayloadRoot(localPart="helloRequest", namespace="http://www.v37zen.prepayment.web.com/hello")
	@ResponsePayload
	public Element hello(@RequestPayload Element in){
		logger.debug("hello ws called\n" + in);
		logger.debug(new XMLOutputter().outputString(in));
				
		Element respons = new Element("helloResponse", in.getNamespace() );
		Element helloMsg = new Element("helloMsg", in.getNamespace() );
		helloMsg.setText("Hello!!!!!");
		respons.addContent(helloMsg);
		
		return respons;
	}
}
