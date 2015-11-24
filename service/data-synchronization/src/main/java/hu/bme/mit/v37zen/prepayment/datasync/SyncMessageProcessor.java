package hu.bme.mit.v37zen.prepayment.datasync;

import org.springframework.context.ApplicationContextAware;
import org.w3c.dom.Node;

public interface SyncMessageProcessor extends Runnable, ApplicationContextAware {

	public void setXmlNode(Node xmlNode);
}
