package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.Balance;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BalanceRepository extends JpaRepository<Balance,Long> {

	public List<Balance> findById(Long id);
	
	@Query("select b from PrepaymentAccount pa inner join pa.balance b where pa.accountMRID = :id")
	public List<Balance> findByAccountId(@Param("id") String accountMRID);
	
	@Query("select b from PrepaymentAccount pa inner join pa.balance b where pa.accountMRID = :id order by b.date desc")
	public List<Balance> findByAccountIdOrderByDateDesc(@Param("id") String accountMRID, Pageable pageable);
}
