package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrepaymentExceptionRepository extends JpaRepository<PrepaymentException, Long>{

	
}
