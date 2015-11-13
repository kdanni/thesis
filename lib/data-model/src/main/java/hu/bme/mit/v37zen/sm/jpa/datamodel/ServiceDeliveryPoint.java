package hu.bme.mit.v37zen.sm.jpa.datamodel;

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
	public String toString() {
		return "ServiceDeliveryPoint [mRID= " + mRID + ", universalId=" + universalId
				+ ", virtualInd=" + virtualInd + ", serviceType=" + serviceType
				+ ", premiseId=" + premiseId + ", billingHoldFlag="
				+ billingHoldFlag + ", parameters=" + parameters + "]";
	}

	
	
	
	
	
}
