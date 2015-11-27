package hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.jpa.repositories.MeterAssetRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeterAsssetValidator {

	@Autowired
	private MeterAssetRepository meterAssetRepository;
	
	public boolean isMeterExist(String mRID) throws ValidationException{
		
		List<MeterAsset> ml = meterAssetRepository.findByMRID(mRID);
		if(ml.size() > 1){
			throw new ValidationException("More than one MeterAsset exist with the mRID of " + mRID + ".", ml);
		}
		return ml.size() == 1;
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

}
