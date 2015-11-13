package hu.bme.mit.v37zen.prepayment.integration.xpath;

import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.prepayment.util.xml.XPathUtil;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.w3c.dom.Node;

public class RoutingRule {	
	public final static Logger logger = LoggerFactory.getLogger(RoutingRule.class);

	private NamespaceHandler namespaceHandler;
	
	private String contentSelectorString;

	private Pattern exceptedContent;

	private MessageChannel route;

	public RoutingRule(String contentSelectorString,
			String exceptedContentRegex, MessageChannel route, NamespaceHandler namespaces) {
		super();
		this.contentSelectorString = contentSelectorString;
		this.exceptedContent = Pattern.compile(exceptedContentRegex);
		this.namespaceHandler = namespaces;
		this.route = route;
	}

	public RoutingRule(String contentSelectorString, Pattern exceptedContent,
			MessageChannel route, NamespaceHandler namespaces) {
		super();
		this.contentSelectorString = contentSelectorString;
		this.exceptedContent = exceptedContent;
		this.namespaceHandler = namespaces;
		this.route = route;
	}
	
	public boolean evaluate(Node node) {
		String content = XPathUtil.evaluateAsString(contentSelectorString, node, namespaceHandler.getNamespaces());
		return exceptedContent.matcher(content).matches();
	}

	public String getContentSelectorString() {
		return contentSelectorString;
	}

	public void setContentSelectorString(String contentSelectorString) {
		this.contentSelectorString = contentSelectorString;
	}

	public Pattern getExceptedContent() {
		return exceptedContent;
	}

	public void setExceptedContent(String exceptedContentRegex) {
		this.exceptedContent = Pattern.compile(exceptedContentRegex);
	}

	public void setExceptedContent(Pattern exceptedContent) {
		this.exceptedContent = exceptedContent;
	}

	public MessageChannel getRoute() {
		return route;
	}

	public void setRoute(MessageChannel route) {
		this.route = route;
	}

	public NamespaceHandler getNamespaceHandler() {
		return namespaceHandler;
	}

	public void setNamespaceHandler(NamespaceHandler namespaceHandler) {
		this.namespaceHandler = namespaceHandler;
	}
}
