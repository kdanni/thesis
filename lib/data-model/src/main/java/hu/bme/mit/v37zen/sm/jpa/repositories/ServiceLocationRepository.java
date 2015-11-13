package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.jpa.datamodel.ServiceLocation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceLocationRepository extends JpaRepository<ServiceLocation, Long> {
	
	public List<ServiceLocation> findById(Long id);
	
	public List<ServiceLocation> findByMRID(String mRID);

	public List<ServiceLocation> findByAddressGeneral(String addressGeneral);
	
	public List<ServiceLocation> findByCity(String city);
	
	public List<ServiceLocation> findByCountry(String country);
	
	public List<ServiceLocation> findByPostalCode(String postalCode);	
	
	@Query("SELECT s FROM ServiceLocation s WHERE s.stateOrProvince = :param")
	public List<ServiceLocation> findByStateOrProvince(@Param("param") String stateOrProvince);
	
	public List<ServiceLocation> findByTimeZone(String timeZone);
	
}
