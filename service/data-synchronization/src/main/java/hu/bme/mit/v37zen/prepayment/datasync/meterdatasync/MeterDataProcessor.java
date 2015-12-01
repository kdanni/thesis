package hu.bme.mit.v37zen.prepayment.datasync.meterdatasync;

import org.springframework.context.ApplicationContextAware;
import org.w3c.dom.Node;

public interface MeterDataProcessor extends Runnable, ApplicationContextAware {

	public void setXmlNode(Node xmlNode);
}
