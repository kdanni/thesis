package hu.bme.mit.v37zen.sm.datamodel.smartmetering;

import hu.bme.mit.v37zen.sm.datamodel.BaseEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity  
public class Route extends BaseEntity {

	private static final long serialVersionUID = -4563146242970495790L;

	private String type;
	
	private String status;
	
	private String billingCycle;
	
	private String readingCycle;
	
	private String reconciliationLockFlag;
	
	@OneToMany(cascade={CascadeType.ALL})
	private
	List<Parameter> parameters = new ArrayList<Parameter>();

	public Route() {
		super();
	}
	
	public Route(String mRID) {
		super(mRID);
	}
	
	public Route(String mRID, String type, String status, String billingCycle,
			String readingCycle, String reconciliationLockFlag,
			List<Parameter> parameters) {
		super(mRID);
		this.type = type;
		this.status = status;
		this.billingCycle = billingCycle;
		this.readingCycle = readingCycle;
		this.reconciliationLockFlag = reconciliationLockFlag;
		this.parameters = parameters;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillingCycle() {
		return billingCycle;
	}

	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}

	public String getReadingCycle() {
		return readingCycle;
	}

	public void setReadingCycle(String readingCycle) {
		this.readingCycle = readingCycle;
	}

	public String getReconciliationLockFlag() {
		return reconciliationLockFlag;
	}

	public void setReconciliationLockFlag(String reconciliationLockFlag) {
		this.reconciliationLockFlag = reconciliationLockFlag;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(Parameter parameter){
		this.parameters.add(parameter);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((billingCycle == null) ? 0 : billingCycle.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result
				+ ((readingCycle == null) ? 0 : readingCycle.hashCode());
		result = prime
				* result
				+ ((reconciliationLockFlag == null) ? 0
						: reconciliationLockFlag.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Route other = (Route) obj;
		if (billingCycle == null) {
			if (other.billingCycle != null)
				return false;
		} else if (!billingCycle.equals(other.billingCycle))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (readingCycle == null) {
			if (other.readingCycle != null)
				return false;
		} else if (!readingCycle.equals(other.readingCycle))
			return false;
		if (reconciliationLockFlag == null) {
			if (other.reconciliationLockFlag != null)
				return false;
		} else if (!reconciliationLockFlag.equals(other.reconciliationLockFlag))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [type=" + type + ", status=" + status + ", billingCycle="
				+ billingCycle + ", readingCycle=" + readingCycle
				+ ", reconciliationLockFlag=" + reconciliationLockFlag
				+ ", parameters=" + parameters + ", id=" + id + ", mRID="
				+ mRID + "]";
	}
}
