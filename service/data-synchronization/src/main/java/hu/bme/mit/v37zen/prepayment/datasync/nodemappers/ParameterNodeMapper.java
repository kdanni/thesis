package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import java.text.DateFormat;
import java.util.Map;

import hu.bme.mit.v37zen.prepayment.util.datetime.DateTimeUtil;
import hu.bme.mit.v37zen.sm.jpa.datamodel.Parameter;

import org.springframework.xml.xpath.NodeMapper;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ParameterNodeMapper implements NodeMapper<Parameter>{
	
	private Map<String,String> namespaces;
	private String nameSpace;
	private DateFormat dateFormat;

	public ParameterNodeMapper(Map<String,String> namespaces, String nameSpace, DateFormat dateFormat) {
		this.namespaces = namespaces;
		this.nameSpace = nameSpace;
		this.dateFormat = dateFormat;
	}

	public Parameter mapNode(Node node, int nodeNum) throws DOMException {
		
		Parameter param = new Parameter();
		
		XPathExpression expr = XPathExpressionFactory.
				createXPathExpression(".//"+nameSpace+":name/text()", namespaces);
		param.setName(expr.evaluateAsString(node));
		
		expr = XPathExpressionFactory.
				createXPathExpression(".//"+nameSpace+":value/text()", namespaces);
		param.setValue(expr.evaluateAsString(node));
		
		expr = XPathExpressionFactory.
				createXPathExpression(".//"+nameSpace+":status/text()", namespaces);
		param.setStatus(expr.evaluateAsString(node));
		
		expr = XPathExpressionFactory.
				createXPathExpression(".//"+nameSpace+":startDate/text()", namespaces);
		param.setStartDate(DateTimeUtil.stringToDate(expr.evaluateAsString(node), dateFormat));
		
		return param;
	}

}
