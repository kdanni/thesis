package hu.bme.mit.v37zen.sm.datamodel.smartmetering;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity  
public class ServiceDeliveryPoint extends BaseEntity {

	private static final long serialVersionUID = 3104660965934934960L;
	
	private String universalId;

	private String virtualInd;
	
	private String serviceType;
		
	private String premiseId;
	
	private String billingHoldFlag;
	
	@OneToMany(cascade={CascadeType.ALL})
	List<Parameter> parameters = new ArrayList<Parameter>();
	
	public ServiceDeliveryPoint() {
		super();
	}
	
	public ServiceDeliveryPoint(String mRID) {
		super(mRID);
	}

	public ServiceDeliveryPoint(String mRID, String virtualInd, String serviceType,
			String premiseId, Parameter parameter) {
		super(mRID);
		this.virtualInd = virtualInd;
		this.serviceType = serviceType;
		this.premiseId = premiseId;
		this.parameters.add(parameter);
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

	public String getVirtualInd() {
		return virtualInd;
	}

	public void setVirtualInd(String virtualInd) {
		this.virtualInd = virtualInd;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getPremiseId() {
		return premiseId;
	}

	public void setPremiseId(String premiseId) {
		this.premiseId = premiseId;
	}

	public String getUniversalId() {
		return universalId;
	}

	public void setUniversalId(String universalId) {
		this.universalId = universalId;
	}

	public String getBillingHoldFlag() {
		return billingHoldFlag;
	}

	public void setBillingHoldFlag(String billingHoldFlag) {
		this.billingHoldFlag = billingHoldFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((billingHoldFlag == null) ? 0 : billingHoldFlag.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result
				+ ((premiseId == null) ? 0 : premiseId.hashCode());
		result = prime * result
				+ ((serviceType == null) ? 0 : serviceType.hashCode());
		result = prime * result
				+ ((universalId == null) ? 0 : universalId.hashCode());
		result = prime * result
				+ ((virtualInd == null) ? 0 : virtualInd.hashCode());
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
		ServiceDeliveryPoint other = (ServiceDeliveryPoint) obj;
		if (billingHoldFlag == null) {
			if (other.billingHoldFlag != null)
				return false;
		} else if (!billingHoldFlag.equals(other.billingHoldFlag))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (premiseId == null) {
			if (other.premiseId != null)
				return false;
		} else if (!premiseId.equals(other.premiseId))
			return false;
		if (serviceType == null) {
			if (other.serviceType != null)
				return false;
		} else if (!serviceType.equals(other.serviceType))
			return false;
		if (universalId == null) {
			if (other.universalId != null)
				return false;
		} else if (!universalId.equals(other.universalId))
			return false;
		if (virtualInd == null) {
			if (other.virtualInd != null)
				return false;
		} else if (!virtualInd.equals(other.virtualInd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceDeliveryPoint [mRID= " + mRID + ", universalId=" + universalId
				+ ", virtualInd=" + virtualInd + ", serviceType=" + serviceType
				+ ", premiseId=" + premiseId + ", billingHoldFlag="
				+ billingHoldFlag + ", parameters=" + parameters + "]";
	}

	
	
	
	
	
}
