package hu.bme.mit.v37zen.prepayment.util.xml;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.xml.xpath.XPathException;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.springframework.xml.xpath.XPathParseException;
import org.w3c.dom.Node;

public class XPathUtil {
	
	public static Logger logger = LoggerFactory.getLogger(XPathUtil.class);

	
	public static List<Node> evaluateAsNodeList(String expression, Node node, Map<String,String> namespaces){
		if(expression == null || expression.isEmpty()){
			return new ArrayList<Node>();
		}
		
		XPathExpression expr = null;
		
		try {
			expr = XPathExpressionFactory.createXPathExpression(expression, namespaces);
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
			return new ArrayList<Node>();
		}
		
		try {
			return expr.evaluateAsNodeList(node);
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return new ArrayList<Node>();
		}
	}
	
	public static String evaluateAsString(String expression, Node node, Map<String,String> namespaces){
		if(expression == null || expression.isEmpty()){
			return "";
		}
		
		XPathExpression expr = null;
		
		try {
			expr = XPathExpressionFactory.createXPathExpression(expression, namespaces);
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
			return null;
		}
		
		try {
			return expr.evaluateAsString(node);
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static Integer evaluateAsInteger(String expression, Node node, Map<String,String> namespaces){
		if(expression == null || expression.isEmpty()){
			return null;
		}
		
		XPathExpression expr = null;
		
		try {
			expr = XPathExpressionFactory.createXPathExpression(expression, namespaces);
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
			return null;
		}
		
		try {
				String s = expr.evaluateAsString(node);
				return Integer.parseInt(s);
			
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static Double evaluateAsDouble(String expression, Node node, Map<String,String> namespaces){
		if(expression == null || expression.isEmpty()){
			return null;
		}
		
		XPathExpression expr = null;
		
		try {
			expr = XPathExpressionFactory.createXPathExpression(expression, namespaces);
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
			return null;
		}
		
		try {
			return expr.evaluateAsNumber(node);
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static Boolean evaluateAsBoolean(String expression, Node node, Map<String,String> namespaces){
		if(expression == null || expression.isEmpty()){
			return null;
		}
		
		XPathExpression expr = null;
		
		try {
			expr = XPathExpressionFactory.createXPathExpression(expression, namespaces);
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
			return null;
		}
		
		try {
			return expr.evaluateAsBoolean(node);
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public static Date evaluateAsDate(String expression, DateFormat format, Node node, Map<String,String> namespaces){
		if(expression == null || expression.isEmpty()){
			return null;
		}
		
		XPathExpression expr = null;
		
		try {
			expr = XPathExpressionFactory.createXPathExpression(expression, namespaces);
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
			return null;
		}
		
		try {
			String datetime = expr.evaluateAsString(node);
			try {
				return format.parse(datetime);
			} catch (ParseException e) {
				logger.warn(e.getMessage(),e);
				return DateTime.parse(datetime).toDate();
			}
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
