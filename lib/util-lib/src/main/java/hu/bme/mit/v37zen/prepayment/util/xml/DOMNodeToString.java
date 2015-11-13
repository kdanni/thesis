package hu.bme.mit.v37zen.prepayment.util.xml;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class DOMNodeToString {
	
	public final static Logger logger = LoggerFactory.getLogger(DOMNodeToString.class);

		
	public DOMNodeToString() {
		super();
	}

	public DOMNodeToString(boolean isIndent, boolean isXMLDeclarationOmited) {
		super();
		this.isIndent = isIndent;
		this.isXMLDeclarationOmited = isXMLDeclarationOmited;
	}

	public boolean isIndent() {
		return isIndent;
	}

	public void setIndent(boolean isIndent) {
		this.isIndent = isIndent;
	}

	public boolean isXMLDeclarationOmited() {
		return isXMLDeclarationOmited;
	}

	public void setXMLDeclarationOmited(boolean isXMLDeclarationOmited) {
		this.isXMLDeclarationOmited = isXMLDeclarationOmited;
	}

	boolean isIndent = true;
	boolean isXMLDeclarationOmited = true;
	
	public String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		
		String isIndentString = isIndent ? "yes" : "no";
		String isXMLDeclarationOmitedString = isXMLDeclarationOmited ? "yes" : "no";
		
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, isIndentString);
			t.setOutputProperty(OutputKeys.INDENT, isXMLDeclarationOmitedString);
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			logger.error("nodeToString Transformer Exception", te);
		}
		return sw.toString();
	}

}
