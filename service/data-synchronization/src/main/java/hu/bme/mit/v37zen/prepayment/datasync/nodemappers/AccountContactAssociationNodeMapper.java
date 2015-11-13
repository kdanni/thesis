package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AssociationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.datetime.DateTimeUtil;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.datamodel.AccountContactAssociation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class AccountContactAssociationNodeMapper extends AbstractNodeMapper<AccountContactAssociation> {
	
	public static Logger logger = LoggerFactory.getLogger(AccountContactAssociationNodeMapper.class);
	
	private AssociationProcessorConfigurator associationProcessorConfigurator;
	
	public AccountContactAssociationNodeMapper(
			AssociationProcessorConfigurator associationProcessorConfigurator, NamespaceHandler namespaces) {
		super(namespaces);
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}

	public AccountContactAssociation mapNode(Node node, int nodeNum)
			throws DOMException {
		
		AccountContactAssociation accContactAss = new AccountContactAssociation();
		StringBuffer buff = new StringBuffer();
		
		String status = evaluate(associationProcessorConfigurator.getAccountContactStatusSelector(), node);
		buff.append("AccountContactAssociation Status: "+ status + '\n');
		accContactAss.setStatus(status);
		
		String startDate = evaluate(associationProcessorConfigurator.getAccountContactStartDateSelector(), node);
		buff.append("AccountContactAssociation StartDate: "+ startDate + '\n');
		accContactAss.setStartDate(DateTimeUtil.stringToDate(startDate, associationProcessorConfigurator.getDateFormat()));
		
		String accountMRID = evaluate(associationProcessorConfigurator.getAccountIdSelector(), node);
		buff.append("AccountContactAssociation AccountMRID: "+ accountMRID + '\n');
		accContactAss.setAccountMRID(accountMRID);
		
		String contactMRID = evaluate(associationProcessorConfigurator.getContactIdSelector(), node);
		buff.append("AccountContactAssociation SDPMRID: "+ contactMRID + '\n');
		accContactAss.setContactMRID(contactMRID);

		logger.debug("[New AccountContactAssociation:]\n" + buff.toString());
		
		return accContactAss;
	}

	public AssociationProcessorConfigurator getAssociationProcessorConfigurator() {
		return associationProcessorConfigurator;
	}

	public void setAssociationProcessorConfigurator(
			AssociationProcessorConfigurator associationProcessorConfigurator) {
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}
	
}
