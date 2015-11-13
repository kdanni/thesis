package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AssociationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.datetime.DateTimeUtil;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.datamodel.AccountSDPAssociation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class AccountSdpAssociationNodeMapper extends AbstractNodeMapper<AccountSDPAssociation> {
	
	public static Logger logger = LoggerFactory.getLogger(AccountNodeMapper.class);
	
	private AssociationProcessorConfigurator associationProcessorConfigurator;
	
	public AccountSdpAssociationNodeMapper(
			AssociationProcessorConfigurator associationProcessorConfigurator,
			NamespaceHandler namespaces) 
	{
		super(namespaces);
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}

	public AccountSDPAssociation mapNode(Node node, int nodeNum)
			throws DOMException {
		
		AccountSDPAssociation accSdpAss = new AccountSDPAssociation();
		StringBuffer buff = new StringBuffer();
		
		String status = evaluate(associationProcessorConfigurator.getAccountSdpStatusSelector(), node);
		buff.append("AccountSDPAssociation Status: "+ status + '\n');
		accSdpAss.setStatus(status);
		
		String startDate = evaluate(associationProcessorConfigurator.getAccountSdpStartDateSelector(), node);
		buff.append("AccountSDPAssociation StartDate: "+ startDate + '\n');
		accSdpAss.setStartDate(DateTimeUtil.stringToDate(startDate, associationProcessorConfigurator.getDateFormat()));
		
		String accountMRID = evaluate(associationProcessorConfigurator.getAccountIdSelector(), node);
		buff.append("AccountSDPAssociation AccountMRID: "+ accountMRID + '\n');
		accSdpAss.setAccountMRID(accountMRID);
		
		String sdpMRID = evaluate(associationProcessorConfigurator.getSdpIdSelector(), node);
		buff.append("AccountSDPAssociation SDPMRID: "+ sdpMRID + '\n');
		accSdpAss.setSdpMRID(sdpMRID);

		logger.debug("[New AccountSDPAssociation:]\n" + buff.toString());
		
		return accSdpAss;
	}

	
	public AssociationProcessorConfigurator getAssociationProcessorConfigurator() {
		return associationProcessorConfigurator;
	}

	public void setAssociationProcessorConfigurator(
			AssociationProcessorConfigurator associationProcessorConfigurator) {
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}
}
