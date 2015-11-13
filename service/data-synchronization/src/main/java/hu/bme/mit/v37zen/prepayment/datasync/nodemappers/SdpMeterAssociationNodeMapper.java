package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AssociationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.datetime.DateTimeUtil;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.datamodel.SdpMeterAssociation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class SdpMeterAssociationNodeMapper extends AbstractNodeMapper<SdpMeterAssociation> {
	
public static Logger logger = LoggerFactory.getLogger(SdpMeterAssociationNodeMapper.class);

	private AssociationProcessorConfigurator associationProcessorConfigurator;	
	
	public SdpMeterAssociationNodeMapper(
			AssociationProcessorConfigurator associationProcessorConfigurator,
			NamespaceHandler namespaces) 
	{
		super(namespaces);
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}

	public SdpMeterAssociation mapNode(Node node, int nodeNum) throws DOMException {
		
		SdpMeterAssociation sdpMeterAss = new SdpMeterAssociation();
		StringBuffer buff = new StringBuffer();
		
		String status = evaluate(associationProcessorConfigurator.getSdpMeterStatusSelector(), node);
		buff.append("SdpMeterAssociation Status: "+ status + '\n');
		sdpMeterAss.setStatus(status);
		
		String startDate = evaluate(associationProcessorConfigurator.getSdpMeterStartDateSelector(), node);
		buff.append("SdpMeterAssociation StartDate: "+ startDate + '\n');
		sdpMeterAss.setStartDate(DateTimeUtil.stringToDate(startDate, associationProcessorConfigurator.getDateFormat()));
		
		String meterAssetMRID = evaluate(associationProcessorConfigurator.getMeterIdSelector(), node);
		buff.append("SdpMeterAssociation MeterAssetMRID: "+ meterAssetMRID + '\n');
		sdpMeterAss.setMeterAssetMRID(meterAssetMRID);
		
		String sdpMRID = evaluate(associationProcessorConfigurator.getSdpIdSelector(), node);
		buff.append("SdpMeterAssociation SDPMRID: "+ sdpMRID + '\n');
		sdpMeterAss.setSdpMRID(sdpMRID);

		logger.debug("[New SdpMeterAssociation:]\n" + buff.toString());
		
		return sdpMeterAss;
		
	}

	public AssociationProcessorConfigurator getAssociationProcessorConfigurator() {
		return associationProcessorConfigurator;
	}

	public void setAssociationProcessorConfigurator(
			AssociationProcessorConfigurator associationProcessorConfigurator) {
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}
}
