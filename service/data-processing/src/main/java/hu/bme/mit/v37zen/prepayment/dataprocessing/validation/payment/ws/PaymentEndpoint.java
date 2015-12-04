package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.payment.ws;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PaymentEndpoint {

	public final static Logger logger = LoggerFactory.getLogger(PaymentEndpoint.class);

	private static final long TIMEOUT = 10000;
	
	@Autowired
	@Qualifier("paymentDataChannelWS")
	private MessageChannel requestChannnel;
	
	@Autowired
	@Qualifier("muxedValidationResultPaymentChannelWS")
	private PollableChannel responseChannel;
	
	@PayloadRoot(localPart="PaymentValidationRequest", namespace="http://www.debug.com/paymentinterface")
	@ResponsePayload
	public Element validation(@RequestPayload Element in){
		Element respons = null;
		logger.debug("hello ws called\n" + in);
		logger.debug(new XMLOutputter().outputString(in));
		try{
			Element paymentMessage = in.getChild("PaymentMessage", in.getNamespace()); 
			Element payload = paymentMessage.getChild("Payload", in.getNamespace());
			Element payment = payload.getChild("payment", in.getNamespace());
			Element account = payment.getChild("account", in.getNamespace());
			Payment p = new Payment();
			p.setAccountId(account.getChildText("mRID",in.getNamespace()));
			p.setValue(Double.parseDouble(payment.getChildText("value",in.getNamespace())));
			p.setCurrency(payment.getChildText("curency",in.getNamespace()));
			p.setDate(DateTime.parse(payment.getChildText("value",in.getNamespace())).toDate());
						
			logger.debug(p.toString());
			
			this.requestChannnel.send(new GenericMessage<Payment>(p));
			
			Message<?> r = this.responseChannel.receive(TIMEOUT);
			System.out.println("PaymentEndpoint, validation respond. " + r);
			if(r != null && r.getPayload() != null && r.getPayload() instanceof Boolean){
				
				respons = new Element("PaymentValidationResponse", in.getNamespace() );
				respons.setText(((Boolean)r.getPayload()).toString());
			}
			
		} catch (Exception w){
			//respons = new Element("PaymentValidationResponse", in.getNamespace() );
			//respons.setText(Boolean.FALSE.toString());
			logger.error(w.getMessage(), w);
		}
		return respons;
	}

	public PollableChannel getResponseChannel() {
		return responseChannel;
	}

	public void setResponseChannel(PollableChannel responseChannel) {
		this.responseChannel = responseChannel;
	}

	public MessageChannel getRequestChannnel() {
		return requestChannnel;
	}

	public void setRequestChannnel(MessageChannel requestChannnel) {
		this.requestChannnel = requestChannnel;
	}
}
