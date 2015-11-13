package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AccountProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.datamodel.Account;
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

public class AccountNodeMapper extends AbstractNodeMapper<Account> {
	
	public static Logger logger = LoggerFactory.getLogger(AccountNodeMapper.class);
	
	private AccountProcessorConfigurator accountProcessorConfigurator;
	
	public AccountNodeMapper(AccountProcessorConfigurator accountProcessorConfigurator, NamespaceHandler namespaces) {
		super(namespaces);
		this.accountProcessorConfigurator = accountProcessorConfigurator;
	}

	public Account mapNode(Node node, int nodeNum) throws DOMException {
		
		Account account = new Account();
		StringBuffer buff = new StringBuffer();
			
		String mRID = evaluate(accountProcessorConfigurator.getMridSelector(), node);
		buff.append("Account MRID: "+ mRID + '\n');
		account.setMRID(mRID);
			
		String name = evaluate(accountProcessorConfigurator.getNameSelector(), node);
		buff.append("Account Name: "+ name + '\n');
		account.setName(name);
		
		String name2 = evaluate(accountProcessorConfigurator.getName2Selector(), node);
		buff.append("Account Name2: "+ name2 + '\n');
		account.setName2(name2);
		
		String status = evaluate(accountProcessorConfigurator.getStatusSelector(), node);
		buff.append("Account Status: "+ status + '\n');
		account.setStatus(status);
		
		String accountType = evaluate(accountProcessorConfigurator.getAccountTypeSelector(), node);
		buff.append("Account AccountType: "+ accountType + '\n');
		account.setAccountType(accountType);
		
		String accountClass = evaluate(accountProcessorConfigurator.getAccountClassSelector(), node);
		buff.append("Account AccountClass: "+ accountClass + '\n');
		account.setAccountClass(accountClass);
		
		String phoneNumber = evaluate(accountProcessorConfigurator.getPhonNumberSelector(), node);
		buff.append("Account PhoneNumber: "+ phoneNumber + '\n');
		account.setPhoneNumber(phoneNumber);
		
		try {
			XPathExpression expr = XPathExpressionFactory.createXPathExpression(
					".//" + accountProcessorConfigurator.getParameterNamespace() + ":parameter",
					namespaces.getNamespaces());
			List<Parameter> paramList = expr.evaluate(node, new ParameterNodeMapper(namespaces.getNamespaces(),
					accountProcessorConfigurator.getParameterNamespace(), accountProcessorConfigurator.getDateFormat()));
			for (Parameter parameter : paramList) {
				account.addParameter(parameter);
			}
			buff.append("Account Parameters: "+ paramList.toString() + '\n');
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
		} catch (XPathException e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("[New Account:]\n" + buff.toString());
		
		return account;
		
	}
	
	public AccountProcessorConfigurator getAccountProcessorConfigurator() {
		return accountProcessorConfigurator;
	}

	public void setAccountProcessorConfigurator(
			AccountProcessorConfigurator accountProcessorConfigurator) {
		this.accountProcessorConfigurator = accountProcessorConfigurator;
	}
}
