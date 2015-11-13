package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AssociationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.datetime.DateTimeUtil;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.datamodel.SdpServiceLocationAssociation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class SdpServiceLocationAssociationNodeMapper extends AbstractNodeMapper<SdpServiceLocationAssociation> {
	
	public static Logger logger = LoggerFactory.getLogger(SdpServiceLocationAssociationNodeMapper.class);
	
	private AssociationProcessorConfigurator associationProcessorConfigurator;
	
	public SdpServiceLocationAssociationNodeMapper(
			AssociationProcessorConfigurator associationProcessorConfigurator, NamespaceHandler namespaces) {
		super(namespaces);
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}

	public SdpServiceLocationAssociation mapNode(Node node, int nodeNum)
			throws DOMException {
		
		SdpServiceLocationAssociation sdpSLAss = new SdpServiceLocationAssociation();
		StringBuffer buff = new StringBuffer();
		
		String status = evaluate(associationProcessorConfigurator.getSdpServiceLocationStatusSelector(), node);
		buff.append("SdpServiceLocationAssociation Status: "+ status + '\n');
		sdpSLAss.setStatus(status);
		
		String startDate = evaluate(associationProcessorConfigurator.getSdpServiceLocationStartDateSelector(), node);
		buff.append("SdpServiceLocationAssociation StartDate: "+ startDate + '\n');
		sdpSLAss.setStartDate(DateTimeUtil.stringToDate(startDate, associationProcessorConfigurator.getDateFormat()));
		
		String serviceLocationMRID = evaluate(associationProcessorConfigurator.getServiceLocationIdSelector(), node);
		buff.append("SdpServiceLocationAssociation MeterAssetMRID: "+ serviceLocationMRID + '\n');
		sdpSLAss.setServiceLocationMRID(serviceLocationMRID);
		
		String sdpMRID = evaluate(associationProcessorConfigurator.getSdpIdSelector(), node);
		buff.append("SdpServiceLocationAssociation SDPMRID: "+ sdpMRID + '\n');
		sdpSLAss.setSdpMRID(sdpMRID);

		logger.debug("[New SdpServiceLocationAssociation:]\n" + buff.toString());
		
		return sdpSLAss;
	}

	public AssociationProcessorConfigurator getAssociationProcessorConfigurator() {
		return associationProcessorConfigurator;
	}

	public void setAssociationProcessorConfigurator(
			AssociationProcessorConfigurator associationProcessorConfigurator) {
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}
}
