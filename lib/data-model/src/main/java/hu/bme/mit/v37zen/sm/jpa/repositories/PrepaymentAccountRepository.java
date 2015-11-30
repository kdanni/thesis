package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrepaymentAccountRepository extends JpaRepository<PrepaymentAccount,Long> {

	public List<PrepaymentAccount> findById(Long id);
	
	public List<PrepaymentAccount> findByAccountMRID(String mRID);
	
	public List<PrepaymentAccount> findBySdpMRID(String mRID);

	@Query("SELECT p FROM PrepaymentAccount p LEFT JOIN FETCH p.meterReadings WHERE p.id = (:id)")
	public PrepaymentAccount findByIdFetchMeterReading(@Param("id") Long id);
	
	@Query("SELECT p FROM PrepaymentAccount p LEFT JOIN FETCH p.payments WHERE p.id = (:id)")
	public PrepaymentAccount findByIdFetchPayment(@Param("id") Long id);
	
	@Query("SELECT p FROM PrepaymentAccount p LEFT JOIN FETCH p.balance WHERE p.id = (:id)")
	public PrepaymentAccount findByIdFetchBalance(@Param("id") Long id);
}
