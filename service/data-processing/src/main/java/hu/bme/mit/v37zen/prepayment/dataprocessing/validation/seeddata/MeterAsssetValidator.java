package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.jpa.repositories.MeterAssetRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpMeterAssociationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MeterAsssetValidator {
	
	@Value("${validation.association.active.status}")
	private String activeStatus;
	
	@Value("${validation.association.inactive.status}")
	private String inactiveStatus;
	
	@Autowired
	private MeterAssetRepository meterAssetRepository;
	
	@Autowired
	private SdpMeterAssociationRepository sdpMeterAssociationRepository;
	
	public boolean isMeterExist(String mRID) throws ValidationException{
		
		List<MeterAsset> ml = meterAssetRepository.findByMRID(mRID);
		if(ml.size() > 1){
			throw new ValidationException("More than one MeterAsset exist with the mRID of " + mRID + ".", ml);
		}
		return ml.size() == 1;
	}
	
	/**
	 * 
	 * @param meterMRID
	 * @return sdpMRID
	 * @throws ValidationException 
	 */
	public String isActiveSdpMeterAssociationExist(String meterMRID) throws ValidationException{
		
		List<SdpMeterAssociation> sdpMeterAssList = sdpMeterAssociationRepository.findByMeterAssetMRID(meterMRID);
		if(sdpMeterAssList.size() == 0){
			String msg = "No SdpMeterAssetAssociation found with the given id!";
			throw new ValidationException(msg);
		}
		SdpMeterAssociation activeSdpMeterAssociation = null;
		int activeCount = 0;
		for (SdpMeterAssociation sdpMeterAssociation : sdpMeterAssList) {
			if(sdpMeterAssociation.getStatus() != null && sdpMeterAssociation.getStatus().equalsIgnoreCase(activeStatus)){
				activeSdpMeterAssociation = sdpMeterAssociation;
				activeCount++; 
			}
		}
		if(activeCount != 1){
			String msg = (activeCount > 1) ? "Multiple active SdpMeterAssetAssociation found!"
					: "No active SdpMeterAssetAssociation found!";
			throw new ValidationException(msg);
		}
		if(activeSdpMeterAssociation != null){
			return activeSdpMeterAssociation.getSdpMRID();
		}
		return null;
	}
	
	public void validate(MeterAsset meterAsset) throws ValidationException {
		
		if(meterAsset.getMRID() == null || meterAsset.getMRID().trim().isEmpty()){
			throw new ValidationException("MeterAsset mRID is empty!", meterAsset);
		}	
		
	}

	public MeterAssetRepository getMeterAssetRepository() {
		return meterAssetRepository;
	}

	public void setMeterAssetRepository(MeterAssetRepository meterAssetRepository) {
		this.meterAssetRepository = meterAssetRepository;
	}

	public SdpMeterAssociationRepository getSdpMeterAssociationRepository() {
		return sdpMeterAssociationRepository;
	}

	public void setSdpMeterAssociationRepository(
			SdpMeterAssociationRepository sdpMeterAssociationRepository) {
		this.sdpMeterAssociationRepository = sdpMeterAssociationRepository;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getInactiveStatus() {
		return inactiveStatus;
	}

	public void setInactiveStatus(String inactiveStatus) {
		this.inactiveStatus = inactiveStatus;
	}

}
