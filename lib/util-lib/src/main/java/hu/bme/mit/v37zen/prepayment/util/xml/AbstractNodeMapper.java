package hu.bme.mit.v37zen.prepayment.util.xml;

import org.springframework.xml.xpath.NodeMapper;
import org.w3c.dom.Node;

public abstract class AbstractNodeMapper<T> implements NodeMapper<T>{
	
	protected NamespaceHandler namespaces;
	
	public AbstractNodeMapper(NamespaceHandler namespaces) {
		this.namespaces = namespaces;
	}

	protected String evaluate(String expression, Node node){
		return XPathUtil.evaluateAsString(expression, node, namespaces.getNamespaces());
	}
	
	public NamespaceHandler getNamespaces() {
		return namespaces;
	}
	
	public void setNamespaces(NamespaceHandler namespaces) {
		this.namespaces = namespaces;
	}
}
