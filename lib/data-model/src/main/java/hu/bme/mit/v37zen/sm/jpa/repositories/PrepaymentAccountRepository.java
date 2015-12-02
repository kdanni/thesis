package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED)
public interface PrepaymentAccountRepository extends JpaRepository<PrepaymentAccount,Long> {

	@Lock(LockModeType.PESSIMISTIC_READ)
	public List<PrepaymentAccount> findById(Long id);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	public List<PrepaymentAccount> findByAccountMRID(String mRID);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	public List<PrepaymentAccount> findBySdpMRID(String mRID);
	
	@Query("SELECT p FROM PrepaymentAccount p LEFT JOIN FETCH p.meterReadings WHERE p.id = (:id)")
	@Lock(LockModeType.PESSIMISTIC_READ)
	public PrepaymentAccount findByIdFetchMeterReading(@Param("id") Long id);
	
	@Query("SELECT p FROM PrepaymentAccount p LEFT JOIN FETCH p.payments WHERE p.id = (:id)")
	@Lock(LockModeType.PESSIMISTIC_READ)
	public PrepaymentAccount findByIdFetchPayment(@Param("id") Long id);
	
	@Query("SELECT p FROM PrepaymentAccount p LEFT JOIN FETCH p.balance WHERE p.id = (:id)")
	@Lock(LockModeType.PESSIMISTIC_READ)
	public PrepaymentAccount findByIdFetchBalance(@Param("id") Long id);
	
}
