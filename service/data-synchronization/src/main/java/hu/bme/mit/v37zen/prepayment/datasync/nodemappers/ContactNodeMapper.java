package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.ContactProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.datamodel.Contact;
import hu.bme.mit.v37zen.sm.jpa.datamodel.Parameter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.xml.xpath.XPathException;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.springframework.xml.xpath.XPathParseException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ContactNodeMapper extends AbstractNodeMapper<Contact> {
	
	public static Logger logger = LoggerFactory.getLogger(ContactNodeMapper.class);

	private ContactProcessorConfigurator contactProcessorConfigurator;
	
	public ContactNodeMapper(ContactProcessorConfigurator contactProcessorConfigurator, NamespaceHandler namespaces) {
		super(namespaces);
		this.contactProcessorConfigurator = contactProcessorConfigurator;
	}
	
	public Contact mapNode(Node node, int nodeNum) throws DOMException {
		
		Contact contact = new Contact();
		StringBuffer buff = new StringBuffer();
		
		String mRID = evaluate(contactProcessorConfigurator.getMridSelector(), node);
		buff.append("Contact MRID: "+ mRID + '\n');
		contact.setMRID(mRID);
		
		String comments = evaluate(contactProcessorConfigurator.getCommentsSelector(), node);
		buff.append("Contact Comments: "+ comments + '\n');
		contact.setComments(comments);

		String emailAddress = evaluate(contactProcessorConfigurator.getEmailAddressSelector(), node);
		buff.append("Contact EmailAddress: "+ emailAddress + '\n');
		contact.setEmailAddress(emailAddress);

		String firstName = evaluate(contactProcessorConfigurator.getFirstNameSelector(), node);
		buff.append("Contact FirstName: "+ firstName + '\n');
		contact.setFirstName(firstName);

		String homePhoneNumber = evaluate(contactProcessorConfigurator.getHomePhoneNumberSelector(), node);
		buff.append("Contact HomePhoneNumber: "+ homePhoneNumber + '\n');
		contact.setHomePhoneNumber(homePhoneNumber);

		String lastName = evaluate(contactProcessorConfigurator.getLastNameSelector(), node);
		buff.append("Contact LastName: "+ lastName + '\n');
		contact.setLastName(lastName);

		String middleName = evaluate(contactProcessorConfigurator.getMiddleNameSelector(), node);
		buff.append("Contact MiddleName: "+ middleName + '\n');
		contact.setMiddleName(middleName);

		String mobilePhoneNumber = evaluate(contactProcessorConfigurator.getMobilePhoneNumberSelector(), node);
		buff.append("Contact MobilePhoneNumber: "+ mobilePhoneNumber + '\n');
		contact.setMobilePhoneNumber(mobilePhoneNumber);

		String secondaryEmailAddress = evaluate(contactProcessorConfigurator.getSecondaryEmailAddressSelector(), node);
		buff.append("Contact SecondaryEmailAddress: "+ secondaryEmailAddress + '\n');
		contact.setSecondaryEmailAddress(secondaryEmailAddress);
	
		String status = evaluate(contactProcessorConfigurator.getStatusSelector(), node);
		buff.append("Contact Status: "+ status + '\n');
		contact.setStatus(status);
		
		try {
			XPathExpression expr = XPathExpressionFactory.createXPathExpression(
					".//" + contactProcessorConfigurator.getParameterNamespace() + ":parameter",
					namespaces.getNamespaces());
			List<Parameter> paramList = expr.evaluate(node, new ParameterNodeMapper(namespaces.getNamespaces(),
					contactProcessorConfigurator.getParameterNamespace(), contactProcessorConfigurator.getDateFormat()));
			for (Parameter parameter : paramList) {
				contact.addParameter(parameter);
			}
			buff.append("Contact Parameters: "+ paramList.toString() + '\n');
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
		} catch (XPathException e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("[New Contact:]\n" + buff.toString());
		
		return contact;
	}

	public ContactProcessorConfigurator getContactProcessorConfigurator() {
		return contactProcessorConfigurator;
	}

	public void setContactProcessorConfigurator(
			ContactProcessorConfigurator contactProcessorConfigurator) {
		this.contactProcessorConfigurator = contactProcessorConfigurator;
	}	
}
