package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SDPValidator {

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
	
	
	public ServiceDeliveryPointRepository getServiceDeliveryPointRepository() {
		return serviceDeliveryPointRepository;
	}

	public void setServiceDeliveryPointRepository(
			ServiceDeliveryPointRepository serviceDeliveryPointRepository) {
		this.serviceDeliveryPointRepository = serviceDeliveryPointRepository;
	}
	
	
	
}
