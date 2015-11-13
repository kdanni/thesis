package hu.bme.mit.v37zen.sm.jpa.repositories;

import java.util.List;

import hu.bme.mit.v37zen.sm.jpa.datamodel.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public List<Account> findById(Long id);

	public List<Account> findByMRID(String mRID);
		
	public List<Account> findByName(String name);
	
	public List<Account> findByName2(String name2);
	
	public List<Account> findByStatus(String status);

	public List<Account> findByAccountType(String accountType);
	
	public List<Account> findByAccountClass(String accountClass);
	
	public List<Account> findByPhoneNumber(String phoneNumber);

}
