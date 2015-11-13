package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.jpa.datamodel.Account;
import hu.bme.mit.v37zen.sm.jpa.datamodel.AccountSDPAssociation;
import hu.bme.mit.v37zen.sm.jpa.datamodel.ServiceDeliveryPoint;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountSDPAssociationRepository extends JpaRepository<AccountSDPAssociation, Long> {
	
	List<AccountSDPAssociation> findById(Long id);
	
	List<AccountSDPAssociation> findByStatus(String status);
	
	List<AccountSDPAssociation> findByStartDate(Date startDate);

	List<AccountSDPAssociation> findByAccountMRID(String mRID);
	
	List<AccountSDPAssociation> findBySdpMRID(String mRID);
	
	List<AccountSDPAssociation> findByAccount(Account account);
	
	List<AccountSDPAssociation> findByServiceDeliveryPoint(ServiceDeliveryPoint sdp);
	
}
