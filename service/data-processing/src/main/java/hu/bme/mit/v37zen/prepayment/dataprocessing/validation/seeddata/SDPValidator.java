package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.jpa.repositories.SdpMeterAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SDPValidator {
	
	@Value("${validation.association.active.status}")
	private String activeStatus;
	
	@Value("${validation.association.inactive.status}")
	private String inactiveStatus;
	
	@Autowired
	private SdpMeterAssociationRepository sdpMeterAssociationRepository;

	@Autowired
	private ServiceDeliveryPointRepository serviceDeliveryPointRepository;

	public boolean isSdpExist(String mRID) throws ValidationException{
		
		List<ServiceDeliveryPoint> sl = serviceDeliveryPointRepository.findByMRID(mRID);
		if(sl.size() > 1){
			throw new ValidationException("More than one SDP exist with the mRID of " + mRID + ".", sl);
		}
		return sl.size() == 1;
	}
	
	public void validate(ServiceDeliveryPoint sdp) throws ValidationException{
		
		if(sdp.getMRID() == null || sdp.getMRID().trim().isEmpty()){
			throw new ValidationException("SDP mRID is empty!", sdp);
		}	
		
	}
	
	/**
	 * 
	 * @param sdpMRID
	 * @return meterMRID
	 * @throws ValidationException 
	 */
	public String isActiveSdpMeterAssociationExist(String sdpMRID) throws ValidationException{
		
		List<SdpMeterAssociation> sdpMeterAssList = sdpMeterAssociationRepository.findBySdpMRID(sdpMRID);
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
	
	public ServiceDeliveryPointRepository getServiceDeliveryPointRepository() {
		return serviceDeliveryPointRepository;
	}

	public void setServiceDeliveryPointRepository(
			ServiceDeliveryPointRepository serviceDeliveryPointRepository) {
		this.serviceDeliveryPointRepository = serviceDeliveryPointRepository;
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

	public SdpMeterAssociationRepository getSdpMeterAssociationRepository() {
		return sdpMeterAssociationRepository;
	}

	public void setSdpMeterAssociationRepository(
			SdpMeterAssociationRepository sdpMeterAssociationRepository) {
		this.sdpMeterAssociationRepository = sdpMeterAssociationRepository;
	}
	
	
	
}
