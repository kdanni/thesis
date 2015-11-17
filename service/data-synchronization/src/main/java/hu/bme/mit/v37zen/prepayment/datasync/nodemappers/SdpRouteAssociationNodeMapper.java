package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AssociationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.datetime.DateTimeUtil;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpRouteAssociation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class SdpRouteAssociationNodeMapper extends AbstractNodeMapper<SdpRouteAssociation>{

	public static Logger logger = LoggerFactory.getLogger(SdpRouteAssociationNodeMapper.class);

	private AssociationProcessorConfigurator associationProcessorConfigurator;	
	
	public SdpRouteAssociationNodeMapper(
			AssociationProcessorConfigurator associationProcessorConfigurator,
			NamespaceHandler namespaces) 
	{
		super(namespaces);
		this.associationProcessorConfigurator = associationProcessorConfigurator;
	}
	
	public SdpRouteAssociation mapNode(Node node, int nodeNum) throws DOMException {
		
		SdpRouteAssociation sdpRouteAssociation = new SdpRouteAssociation();
		StringBuffer buff = new StringBuffer();
				
		String status = evaluate(associationProcessorConfigurator.getSdpRouteStatusSelector(), node);
		buff.append("SdpRouteAssociation Status: "+ status + '\n');
		sdpRouteAssociation.setStatus(status);
		
		String startDate = evaluate(associationProcessorConfigurator.getSdpRouteStartDateSelector(), node);
		buff.append("SdpRouteAssociation StartDate: "+ startDate + '\n');
		sdpRouteAssociation.setStartDate(DateTimeUtil.stringToDate(startDate, associationProcessorConfigurator.getDateFormat()));
		
		String endDate = evaluate(associationProcessorConfigurator.getSdpRouteEndDateSelector(), node);
		buff.append("SdpRouteAssociation EndDate: "+ endDate + '\n');
		sdpRouteAssociation.setEndDate(DateTimeUtil.stringToDate(endDate, associationProcessorConfigurator.getDateFormat()));
		
		String routeMRID = evaluate(associationProcessorConfigurator.getRouteIdSelector(), node);
		buff.append("SdpRouteAssociation RouteMRID: "+ routeMRID + '\n');
		sdpRouteAssociation.setRouteMRID(routeMRID);
		
		String sdpMRID = evaluate(associationProcessorConfigurator.getSdpIdSelector(), node);
		buff.append("SdpRouteAssociation SDPMRID: "+ sdpMRID + '\n');
		sdpRouteAssociation.setSdpMRID(sdpMRID);

		logger.debug("[New SdpRouteAssociation:]\n" + buff.toString());
		
		
		return sdpRouteAssociation;
	}
}
