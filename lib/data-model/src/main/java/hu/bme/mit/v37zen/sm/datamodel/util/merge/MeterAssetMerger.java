package hu.bme.mit.v37zen.sm.datamodel.util.merge;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;

public class MeterAssetMerger {

	public static MeterAsset merge(MeterAsset returnee, MeterAsset theOtherMeter ){
		
		if(theOtherMeter.getSerialNumber() != null && !theOtherMeter.getSerialNumber().trim().isEmpty()){
			returnee.setSerialNumber(theOtherMeter.getSerialNumber());
		}
		if(theOtherMeter.getServiceType() != null && !theOtherMeter.getServiceType().trim().isEmpty()){
			returnee.setServiceType(theOtherMeter.getServiceType());
		}
		if(theOtherMeter.getStatus() != null && !theOtherMeter.getStatus().trim().isEmpty()){
			returnee.setStatus(theOtherMeter.getStatus());
		}
		if(theOtherMeter.getVirtualInd() != null && !theOtherMeter.getVirtualInd().trim().isEmpty()){
			returnee.setVirtualInd(theOtherMeter.getVirtualInd());
		}

		if(theOtherMeter.getFirstRead() != null){
			returnee.setFirstRead(theOtherMeter.getFirstRead());
		}
		if(theOtherMeter.getInstallDate() != null){
			returnee.setInstallDate(theOtherMeter.getInstallDate());
		}
		if(theOtherMeter.getLastRead() != null){
			returnee.setLastRead(theOtherMeter.getLastRead());
		}
		if(theOtherMeter.getManufacturedDate() != null){
			returnee.setManufacturedDate(theOtherMeter.getManufacturedDate());
		}
		if(theOtherMeter.getParameters() != null && !theOtherMeter.getParameters().isEmpty()){
			returnee.getParameters().addAll(theOtherMeter.getParameters());
		}
				
		return returnee;
	}
}
