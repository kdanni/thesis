package hu.bme.mit.v37zen.prepayment.datasync.paymentsync;

import org.springframework.context.ApplicationContextAware;
import org.w3c.dom.Node;

public interface PaymentDataProcessor extends Runnable, ApplicationContextAware{

	public void setXmlNode(Node xmlNode);
}
