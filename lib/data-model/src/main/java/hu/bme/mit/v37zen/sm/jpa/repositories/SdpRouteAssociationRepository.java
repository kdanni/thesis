package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Route;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpRouteAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SdpRouteAssociationRepository extends JpaRepository<SdpRouteAssociation,Long >{

	List<SdpRouteAssociation> findById(Long id);
	
	List<SdpRouteAssociation> findByStatus(String status);
	
	List<SdpRouteAssociation> findByStartDate(Date startDate);

	List<SdpRouteAssociation> findByRouteMRID(String mRID);
	
	List<SdpRouteAssociation> findBySdpMRID(String mRID);
	
	List<SdpRouteAssociation> findByRoute(Route route);
	
	List<SdpRouteAssociation> findByServiceDeliveryPoint(ServiceDeliveryPoint sdp);
}
