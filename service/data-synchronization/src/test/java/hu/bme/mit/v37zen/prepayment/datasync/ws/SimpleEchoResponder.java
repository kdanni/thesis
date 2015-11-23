package hu.bme.mit.v37zen.prepayment.datasync.ws;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.springframework.integration.xml.source.DomSourceFactory;
/**
 * @author Chris Beams
 */
public class SimpleEchoResponder {

	public Source issueResponseFor(DOMSource request) {
		return new DomSourceFactory().createSource(
				"<echoResponse xmlns=\"http://www.springframework.org/spring-ws/samples/echo\">" +
				request.getNode().getNodeValue() + "</echoResponse>");
	}
}