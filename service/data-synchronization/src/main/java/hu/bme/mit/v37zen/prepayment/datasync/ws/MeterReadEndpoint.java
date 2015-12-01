package hu.bme.mit.v37zen.prepayment.datasync.ws;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.output.DOMOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.w3c.dom.Node;

@Endpoint
public class MeterReadEndpoint {

	public final static Logger logger = LoggerFactory.getLogger(MeterReadEndpoint.class);
	
	@Autowired
	@Qualifier(value="meterReadingsWSInChannel")
	private MessageChannel channel;
	
	@PayloadRoot(localPart="MeterDataMessage", namespace="http://www.emeter.com/energyip/meterdatainterface")
	public void meterreadings(@RequestPayload Element in){
		logger.debug("meterreadings ws called\n" + in);
		//logger.debug(new XMLOutputter().outputString(in));
		try {
			channel.send(new GenericMessage<Node>(new DOMOutputter().output(in)));
		} catch (JDOMException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public MessageChannel getChannel() {
		return channel;
	}

	public void setChannel(MessageChannel channel) {
		this.channel = channel;
	}

}
