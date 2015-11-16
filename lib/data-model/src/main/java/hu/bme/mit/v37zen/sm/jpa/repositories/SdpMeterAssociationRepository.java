package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SdpMeterAssociationRepository extends JpaRepository<SdpMeterAssociation, Long> {

	List<SdpMeterAssociation> findById(Long id);
	
	List<SdpMeterAssociation> findByStatus(String status);
	
	List<SdpMeterAssociation> findByStartDate(Date startDate);

	List<SdpMeterAssociation> findByMeterAssetMRID(String mRID);
	
	List<SdpMeterAssociation> findBySdpMRID(String mRID);
	
	List<SdpMeterAssociation> findByMeterAsset(MeterAsset meter);
	
	List<SdpMeterAssociation> findByServiceDeliveryPoint(ServiceDeliveryPoint sdp);
}
