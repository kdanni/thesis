package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterAssetRepository extends JpaRepository<MeterAsset, Long> {

	public List<MeterAsset> findById(Long id);

	public List<MeterAsset> findByMRID(String mRID);
	
	public List<MeterAsset> findByServiceType(String serviceType);
	
	public List<MeterAsset> findByStatus(String status);
	
	public List<MeterAsset> findByVirtualInd(String virtualInd);
	
	public List<MeterAsset> findByManufacturedDate(Date manufacturedDate);
}
