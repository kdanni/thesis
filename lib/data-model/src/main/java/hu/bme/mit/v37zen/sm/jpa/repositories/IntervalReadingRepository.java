package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IntervalReadingRepository extends JpaRepository<IntervalReading, Long> {
	
	
	public List<IntervalReading> findById(Long id);
	
	public List<IntervalReading> findByReadingTypeId(String readingTypeId);
	
	public List<IntervalReading> findByEndTime(Date endTime);
	
	public List<IntervalReading> findByMeterReferenceId(String referenceId);
	
	public List<IntervalReading> findByValid(Boolean status);
	
	public List<IntervalReading> findByProcessed(Boolean status);
	
	public List<IntervalReading> findByArchived(Boolean status);
	
//	@Query("select ir from PrepaymentAccount pa inner join pa.meterReadings ir where pa.accountMRID = :id")
//	public List<IntervalReading> findByAccountId(@Param("id") String accountMRID);
//	
//	@Query("select ir from PrepaymentAccount pa inner join pa.meterReadings ir where pa.accountMRID = :id and ir.processed = '0'")
//	public List<IntervalReading> getIntervalReadingForProccessing(@Param("id") String accountMRID);
	
	public List<IntervalReading> findByMeterReferenceIdAndEndTime(String referenceId, Date endTime);
	
	@Query("select ir from IntervalReading ir where ir.meterReferenceId = :id and ir.processed = '0'")
	public List<IntervalReading> getIntervalReadingForProccessing(@Param("id") String meterMRID);
	
}
//and ir.processed = false
