package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpServiceLocationAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceLocation;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SdpServiceLocationAssociationRepository extends JpaRepository<SdpServiceLocationAssociation, Long>{

	List<SdpServiceLocationAssociation> findById(Long id);
	
	List<SdpServiceLocationAssociation> findByStatus(String status);
	
	List<SdpServiceLocationAssociation> findByStartDate(Date startDate);

	List<SdpServiceLocationAssociation> findByServiceLocationMRID(String mRID);
	
	List<SdpServiceLocationAssociation> findBySdpMRID(String mRID);
	
	List<SdpServiceLocationAssociation> findByServiceLocation(ServiceLocation serviceLocation);
	
	List<SdpServiceLocationAssociation> findByServiceDeliveryPoint(ServiceDeliveryPoint sdp);
}
