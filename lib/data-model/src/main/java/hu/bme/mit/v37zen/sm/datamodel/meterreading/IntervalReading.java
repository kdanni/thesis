package hu.bme.mit.v37zen.sm.datamodel.meterreading;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.BaseEntity;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class IntervalReading extends BaseEntity {

	private static final long serialVersionUID = -2471771834985064822L;
    
	public static final String METER_X_UDC_ASSET_ID = "METER_X_UDC_ASSET_ID";
	
	@ManyToOne(fetch=FetchType.EAGER)
	private MeterAsset meterAsset;
	
	private String meterReferenceId;
	
	private String referenceIdType;
	
	private String referenceIdNamepsace;
	
	private String readingTypeId;
	
	private Double value;
	
	private Boolean valid;
	
	private Boolean processed;
	
	private Boolean archived;
	
	private Integer intervalLength;
	
	private String readingBlock;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date insertTime;
	
	public IntervalReading() {
		super();
		this.insertTime = new Date();
	}

	public IntervalReading(MeterAsset meterAsset, String meterReferenceId,
			String referenceIdType, String referenceIdNamepsace,
			String readingTypeId, Double value, Boolean valid,
			Boolean processed, Boolean archived, int intervalLength,
			String readingBlock, Date endTime) {
		super();
		this.meterAsset = meterAsset;
		this.meterReferenceId = meterReferenceId;
		this.referenceIdType = referenceIdType;
		this.referenceIdNamepsace = referenceIdNamepsace;
		this.readingTypeId = readingTypeId;
		this.value = value;
		this.valid = valid;
		this.processed = processed;
		this.archived = archived;
		this.intervalLength = intervalLength;
		this.readingBlock = readingBlock;
		this.endTime = endTime;

		this.insertTime = new Date();
	}

	public String getReadingTypeId() {
		return readingTypeId;
	}

	public void setReadingTypeId(String readingTypeId) {
		this.readingTypeId = readingTypeId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public MeterAsset getMeterAsset() {
		return meterAsset;
	}

	public void setMeterAsset(MeterAsset meterAsset) {
		this.meterAsset = meterAsset;
	}

	public String getReferenceId() {
		return meterReferenceId;
	}

	public void setReferenceId(String meterReferenceId) {
		this.meterReferenceId = meterReferenceId;
	}

	public String getReferenceIdType() {
		return referenceIdType;
	}

	public void setReferenceIdType(String referenceIdType) {
		this.referenceIdType = referenceIdType;
	}

	public String getReferenceIdNamepsace() {
		return referenceIdNamepsace;
	}

	public void setReferenceIdNamepsace(String referenceIdNamepsace) {
		this.referenceIdNamepsace = referenceIdNamepsace;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public int getIntervalLength() {
		return intervalLength;
	}

	public void setIntervalLength(int intervalLength) {
		this.intervalLength = intervalLength;
	}

	public String getReadingBlock() {
		return readingBlock;
	}

	public void setReadingBlock(String readingBlock) {
		this.readingBlock = readingBlock;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((archived == null) ? 0 : archived.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((intervalLength == null) ? 0 : intervalLength.hashCode());
		result = prime * result
				+ ((meterAsset == null) ? 0 : meterAsset.hashCode());
		result = prime
				* result
				+ ((meterReferenceId == null) ? 0 : meterReferenceId.hashCode());
		result = prime * result
				+ ((processed == null) ? 0 : processed.hashCode());
		result = prime * result
				+ ((readingBlock == null) ? 0 : readingBlock.hashCode());
		result = prime * result
				+ ((readingTypeId == null) ? 0 : readingTypeId.hashCode());
		result = prime
				* result
				+ ((referenceIdNamepsace == null) ? 0 : referenceIdNamepsace
						.hashCode());
		result = prime * result
				+ ((referenceIdType == null) ? 0 : referenceIdType.hashCode());
		result = prime * result + ((valid == null) ? 0 : valid.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntervalReading other = (IntervalReading) obj;
		if (archived == null) {
			if (other.archived != null)
				return false;
		} else if (!archived.equals(other.archived))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (intervalLength == null) {
			if (other.intervalLength != null)
				return false;
		} else if (!intervalLength.equals(other.intervalLength))
			return false;
		if (meterAsset == null) {
			if (other.meterAsset != null)
				return false;
		} else if (!meterAsset.equals(other.meterAsset))
			return false;
		if (meterReferenceId == null) {
			if (other.meterReferenceId != null)
				return false;
		} else if (!meterReferenceId.equals(other.meterReferenceId))
			return false;
		if (processed == null) {
			if (other.processed != null)
				return false;
		} else if (!processed.equals(other.processed))
			return false;
		if (readingBlock == null) {
			if (other.readingBlock != null)
				return false;
		} else if (!readingBlock.equals(other.readingBlock))
			return false;
		if (readingTypeId == null) {
			if (other.readingTypeId != null)
				return false;
		} else if (!readingTypeId.equals(other.readingTypeId))
			return false;
		if (referenceIdNamepsace == null) {
			if (other.referenceIdNamepsace != null)
				return false;
		} else if (!referenceIdNamepsace.equals(other.referenceIdNamepsace))
			return false;
		if (referenceIdType == null) {
			if (other.referenceIdType != null)
				return false;
		} else if (!referenceIdType.equals(other.referenceIdType))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IntervalReading [meterAsset=" + meterAsset
				+ ", meterReferenceId=" + meterReferenceId
				+ ", referenceIdType=" + referenceIdType
				+ ", referenceIdNamepsace=" + referenceIdNamepsace
				+ ", readingTypeId=" + readingTypeId + ", value=" + value
				+ ", valid=" + valid + ", processed=" + processed
				+ ", archived=" + archived + ", intervalLength="
				+ intervalLength + ", readingBlock=" + readingBlock
				+ ", endTime=" + endTime + ", insertTime=" + insertTime
				+ ", id=" + id + ", mRID=" + mRID + "]";
	}
	    
}
