package hu.bme.mit.v37zen.prepayment.payment;

import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.jpa.repositories.PaymentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.xml.xpath.XPathException;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.springframework.xml.xpath.XPathParseException;
import org.w3c.dom.Node;

public class PaymentDataProcessoetrImpl implements PaymentDataProcessor {

	public static Logger logger = LoggerFactory.getLogger(PaymentDataProcessoetrImpl.class);
	
	private Node xmlNode;
	
	private NamespaceHandler namespaces;
	
	private PaymentConfigurator paymentConfigurator;
	
	private ApplicationContext applicationContext;
	
    public PaymentDataProcessoetrImpl(NamespaceHandler namespaceHandler) {
		this.namespaces = namespaceHandler;
	}
		
	@Override
	public void run() {
		
		if(this.xmlNode == null){
			logger.warn("Payment message is null!"); 
			return;
		}
		logger.info("Payment processing has started."); 
		try {
				
			List<Payment> payments = new ArrayList<Payment>();
			
			XPathExpression expr = createXPathExpression(this.paymentConfigurator.getPaymentSelector(), namespaces.getNamespaces());
			if (expr != null){
				try {
					payments = expr.evaluate(xmlNode, new PaymentNodeMapper(paymentConfigurator, namespaces));
				} catch (XPathException e) {
					logger.error(e.getMessage());
					return;
				}
			}
			if(payments != null && !payments.isEmpty()){
				PaymentRepository repo = applicationContext.getBean(PaymentRepository.class);
				repo.save(payments);
			}
			
			logger.info("Payment reading processing has finished."); 
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.info("Payment reading processing has failed."); 
		}
	}

	private XPathExpression createXPathExpression(String expression, Map<String, String> namespaces) {
		if(expression == null || expression.isEmpty()){
			return null;
		}		
		try{	
			return XPathExpressionFactory.createXPathExpression(expression, namespaces);
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		this.applicationContext = applicationContext;
	}

	@Override
	public void setXmlNode(Node xmlNode) {
		this.xmlNode = xmlNode;
	}

	public Node getXmlNode() {
		return xmlNode;
	}

	public NamespaceHandler getNamespaces() {
		return namespaces;
	}

	public void setNamespaces(NamespaceHandler namespaces) {
		this.namespaces = namespaces;
	}

	public PaymentConfigurator getPaymentConfigurator() {
		return paymentConfigurator;
	}

	public void setPaymentConfigurator(PaymentConfigurator paymentConfigurator) {
		this.paymentConfigurator = paymentConfigurator;
	}
}
