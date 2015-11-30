package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

	public List<Payment> findById(Long id);
	
	@Query("select p from PrepaymentAccount pa inner join pa.payments p where pa.accountMRID = :id")
	public List<Payment> findByAccountId(@Param("id") String accountMRID);
		
	@Query("select p from PrepaymentAccount pa inner join pa.payments p where pa.accountMRID = :id and p.processed = '0'")
	public List<Payment> getPaymentForProccessing(@Param("id") String accountMRID);
}
