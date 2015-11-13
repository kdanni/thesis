package hu.bme.mit.v37zen.sm.jpa.repositories;

import hu.bme.mit.v37zen.sm.jpa.datamodel.MeterAsset;
import hu.bme.mit.v37zen.sm.jpa.datamodel.meterreading.IntervalReading;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervalReadingRepository extends JpaRepository<IntervalReading, Long> {
	
	
	public List<IntervalReading> findById(Long id);
	
	public List<IntervalReading> findByReadingTypeId(String readingTypeId);
	
	public List<IntervalReading> findByEndTime(Date endTime);
	
	public List<IntervalReading> findByMeterReferenceId(String referenceId);
	
	public List<IntervalReading> findByMeterAsset(MeterAsset meter);
	
	public List<IntervalReading> findByValid(Boolean status);
	
	public List<IntervalReading> findByProcessed(Boolean status);
	
	public List<IntervalReading> findByArchived(Boolean status);
	
}
