package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Route;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Long> {
	
	public List<Route> findById(Long id);

	public List<Route> findByMRID(String mRID);
	
	public List<Route> findByBillingCycle(String billingCycle);
	
	public List<Route> findByStatus(String status);
	
	public List<Route> findByReadingCycle(String readingCycle);
}
