package hu.bme.mit.v37zen.sm.datamodel.util.merge;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;

public class SdpMerger {

	public static ServiceDeliveryPoint merge(ServiceDeliveryPoint returnee, ServiceDeliveryPoint theOtherSdp ){
		
		if(theOtherSdp.getBillingHoldFlag() != null && !theOtherSdp.getBillingHoldFlag().trim().isEmpty()){
			returnee.setBillingHoldFlag(theOtherSdp.getBillingHoldFlag());
		}
		if(theOtherSdp.getPremiseId() != null && !theOtherSdp.getPremiseId().trim().isEmpty()){
			returnee.setPremiseId(theOtherSdp.getPremiseId());
		}
		if(theOtherSdp.getServiceType() != null && !theOtherSdp.getServiceType().trim().isEmpty()){
			returnee.setServiceType(theOtherSdp.getServiceType());
		}
		if(theOtherSdp.getUniversalId() != null && !theOtherSdp.getUniversalId().trim().isEmpty()){
			returnee.setUniversalId(theOtherSdp.getUniversalId());
		}
		if(theOtherSdp.getVirtualInd() != null && !theOtherSdp.getVirtualInd().trim().isEmpty()){
			returnee.setVirtualInd(theOtherSdp.getVirtualInd());
		}
		if(theOtherSdp.getParameters() != null && !theOtherSdp.getParameters().isEmpty()){
			returnee.getParameters().addAll(theOtherSdp.getParameters());
		}
				
		return returnee;
	}
}
