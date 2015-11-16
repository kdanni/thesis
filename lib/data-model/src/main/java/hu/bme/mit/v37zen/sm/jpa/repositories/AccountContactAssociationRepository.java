package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountContactAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Contact;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountContactAssociationRepository extends JpaRepository<AccountContactAssociation, Long>{

	List<AccountContactAssociation> findById(Long id);
	
	List<AccountContactAssociation> findByStatus(String status);
	
	List<AccountContactAssociation> findByStartDate(Date startDate);

	List<AccountContactAssociation> findByAccountMRID(String mRID);
	
	List<AccountContactAssociation> findByContactMRID(String mRID);
	
	List<AccountContactAssociation> findByAccount(Account account);
	
	List<AccountContactAssociation> findByContact(Contact contact);
}
