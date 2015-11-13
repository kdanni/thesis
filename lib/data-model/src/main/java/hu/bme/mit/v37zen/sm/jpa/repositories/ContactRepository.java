package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.jpa.datamodel.Contact;
import hu.bme.mit.v37zen.sm.jpa.datamodel.ServiceLocation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	public List<ServiceLocation> findById(Long id);
	
	public List<ServiceLocation> findByMRID(String mRID);

	public List<ServiceLocation> findByEmailAddress(String emailAddress);

	public List<ServiceLocation> findByHomePhoneNumber(String homePhoneNumber);
	
	public List<ServiceLocation> findByMobilePhoneNumber(String mobilePhoneNumber);
	
	@Query("SELECT c FROM Contact c WHERE c.firstName = :firstName AND c.lastName = :lastName")
	public List<ServiceLocation> findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	
}
