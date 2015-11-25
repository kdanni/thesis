package hu.bme.mit.v37zen.prepayment.payment;

import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.prepayment.util.xml.XPathUtil;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class PaymentNodeMapper extends AbstractNodeMapper<Payment> {
	
	public static Logger logger = LoggerFactory.getLogger(PaymentNodeMapper.class);	

	private PaymentConfigurator paymentConfigurator;
	
	public PaymentNodeMapper(PaymentConfigurator paymentConfigurator, NamespaceHandler namespaces) {
		super(namespaces);
		this.paymentConfigurator = paymentConfigurator;
	}

	@Override
	public Payment mapNode(Node node, int nodeNum) throws DOMException {
		
		Payment payment = new Payment();
		StringBuffer buff = new StringBuffer();
		
		String accId = evaluate(paymentConfigurator.getAccountIdSelector(), node);
		buff.append("Payment account ID: "+ accId + '\n');
		payment.setMRID(accId);
		
		Double value = XPathUtil.evaluateAsDouble(paymentConfigurator.getValueSelector(), node, namespaces.getNamespaces());
		buff.append("Payment value: "+ value + '\n');
		payment.setValue(value);

		String currency = evaluate(paymentConfigurator.getCurencySelector(), node);
		buff.append("Payment currency: "+ currency + '\n');
		payment.setMRID(accId);
		
		Date date = XPathUtil.evaluateAsDate(paymentConfigurator.getValueSelector(),
				paymentConfigurator.getDateFormat(), node, namespaces.getNamespaces());
		buff.append("Payment date: "+ date + '\n'); 
		payment.setDate(date);
		
		String status = evaluate(paymentConfigurator.getStatusSelector(), node);
		buff.append("Route Status: "+ status + '\n');
		payment.setStatus(status);
		
		return payment;
	}

	public PaymentConfigurator getPaymentConfigurator() {
		return paymentConfigurator;
	}

	public void setPaymentConfigurator(PaymentConfigurator paymentConfigurator) {
		this.paymentConfigurator = paymentConfigurator;
	}

}
