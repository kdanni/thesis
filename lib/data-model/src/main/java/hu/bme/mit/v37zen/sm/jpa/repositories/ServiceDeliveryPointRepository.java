package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.jpa.datamodel.ServiceDeliveryPoint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDeliveryPointRepository extends JpaRepository<ServiceDeliveryPoint, Long> {
	
	public List<ServiceDeliveryPoint> findById(Long id);

	public List<ServiceDeliveryPoint> findByMRID(String mRID);
	
	public List<ServiceDeliveryPoint> findByServiceType(String serviceType);
	
	public List<ServiceDeliveryPoint> findByVirtualInd(String virtualInd);
	
	public List<ServiceDeliveryPoint> findByPremiseId(String premiseId);
	
	public List<ServiceDeliveryPoint> findByBillingHoldFlag(String billingHoldFlag);
	
	//@Query("SELECT s FROM ServiceDeliveryPoint s, Parameter p WHERE s.id = p. = :param")
	//public List<ServiceDeliveryPoint> findByParameter(@Param("paramName") String parameterName, @Param("paramValue") String parameterValue);
	
}
