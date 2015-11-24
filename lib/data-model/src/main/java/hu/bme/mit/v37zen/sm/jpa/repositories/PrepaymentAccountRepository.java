package hu.bme.mit.v37zen.sm.jpa.repositories;

import java.util.List;

import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrepaymentAccountRepository extends JpaRepository<PrepaymentAccount,Long> {

	public List<PrepaymentAccount> findById(Long id);
}
