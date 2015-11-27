package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ScheduledForRevalidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SDPMeterAssociationValidator {
	
	@Autowired
	private SDPValidator sdpValidator;
	
	@Autowired
	private MeterAsssetValidator meterAsssetValidator;

	public void validate(SdpMeterAssociation sdpMeterAssociation,
			HashMap<String, ServiceDeliveryPoint> newSDPs,
			HashMap<String, MeterAsset> newMeters) throws ValidationException, ScheduledForRevalidationException  {
		
		String sdpMRID;
		if(sdpMeterAssociation.getServiceDeliveryPoint() != null){
			if(sdpMeterAssociation.getSdpMRID() != null && !sdpMeterAssociation.getSdpMRID().trim().isEmpty() ){
				if(sdpMeterAssociation.getServiceDeliveryPoint().getMRID() != sdpMeterAssociation.getSdpMRID()){
					throw new ValidationException("SdpMeterAssociation is inconsistent.", sdpMeterAssociation);
				} else {
					sdpMRID = sdpMeterAssociation.getServiceDeliveryPoint().getMRID();
				}
			} else{
				sdpMRID = sdpMeterAssociation.getServiceDeliveryPoint().getMRID();
			}			
		} else {
			sdpMRID = sdpMeterAssociation.getSdpMRID();
		}
		
		String meterMRID;
		if(sdpMeterAssociation.getMeterAsset() != null){
			if(sdpMeterAssociation.getMeterAssetMRID() != null && !sdpMeterAssociation.getMeterAssetMRID().trim().isEmpty() ){
				if(sdpMeterAssociation.getMeterAsset().getMRID() != sdpMeterAssociation.getMeterAssetMRID()){
					throw new ValidationException("SdpMeterAssociation is inconsistent.", sdpMeterAssociation);
				} else {
					meterMRID = sdpMeterAssociation.getMeterAsset().getMRID();
				}
			} else{
				meterMRID = sdpMeterAssociation.getMeterAsset().getMRID();
			}			
		} else {
			meterMRID = sdpMeterAssociation.getMeterAssetMRID();
		}

		boolean sdpOK = newSDPs.containsKey(sdpMRID);
		boolean meterOK = newMeters.containsKey(meterMRID);
		

		if(!sdpOK){
			sdpOK = this.sdpValidator.isSdpExist(sdpMRID);
		}
		if(!meterOK){
			meterOK = this.meterAsssetValidator.isMeterExist(meterMRID);
		}
		
		if(!meterOK || !sdpOK){
			throw new ScheduledForRevalidationException(null);
		}
		
		
	}

	public SDPValidator getSdpValidator() {
		return sdpValidator;
	}

	public void setSdpValidator(SDPValidator sdpValidator) {
		this.sdpValidator = sdpValidator;
	}

	public MeterAsssetValidator getMeterAsssetValidator() {
		return meterAsssetValidator;
	}

	public void setMeterAsssetValidator(MeterAsssetValidator meterAsssetValidator) {
		this.meterAsssetValidator = meterAsssetValidator;
	}

}
