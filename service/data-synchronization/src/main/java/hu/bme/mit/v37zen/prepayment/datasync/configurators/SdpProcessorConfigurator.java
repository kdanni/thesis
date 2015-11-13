package hu.bme.mit.v37zen.prepayment.datasync.configurators;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * 
 * @author Dániel Kiss
 *
 */
@ManagedResource(
        objectName="bean:name=SyncMessageSDPProcessorConfigurator",
        description="SdpProcessorConfigurator")
public class SdpProcessorConfigurator extends BaseConfigurator {
	
	
	private String sdpSelector;
	private String mridSelector;
	private String universalIdSelector;
	private String virtualIndSelector;	
	private String serviceTypeSelector;		
	private String premiseIdSelector;	
	private String billingHoldFlagSelector;
	
	private String parameterNamespace;

	@ManagedAttribute
	public String getSdpSelector() {
		return sdpSelector;
	}

	@ManagedAttribute
	public void setSdpSelector(String sdpSelector) {
		this.sdpSelector = sdpSelector;
	}

	@ManagedAttribute
	public String getMridSelector() {
		return mridSelector;
	}

	@ManagedAttribute
	public void setMridSelector(String mridSelector) {
		this.mridSelector = mridSelector;
	}

	@ManagedAttribute
	public String getUniversalIdSelector() {
		return universalIdSelector;
	}

	@ManagedAttribute
	public void setUniversalIdSelector(String universalIdSelector) {
		this.universalIdSelector = universalIdSelector;
	}

	@ManagedAttribute
	public String getVirtualIndSelector() {
		return virtualIndSelector;
	}

	@ManagedAttribute
	public void setVirtualIndSelector(String virtualIndSelector) {
		this.virtualIndSelector = virtualIndSelector;
	}

	@ManagedAttribute
	public String getServiceTypeSelector() {
		return serviceTypeSelector;
	}

	@ManagedAttribute
	public void setServiceTypeSelector(String serviceTypeSelector) {
		this.serviceTypeSelector = serviceTypeSelector;
	}

	@ManagedAttribute
	public String getPremiseIdSelector() {
		return premiseIdSelector;
	}

	@ManagedAttribute
	public void setPremiseIdSelector(String premiseIdSelector) {
		this.premiseIdSelector = premiseIdSelector;
	}

	@ManagedAttribute
	public String getBillingHoldFlagSelector() {
		return billingHoldFlagSelector;
	}

	@ManagedAttribute
	public void setBillingHoldFlagSelector(String billingHoldFlagSelector) {
		this.billingHoldFlagSelector = billingHoldFlagSelector;
	}

	@ManagedAttribute
	public String getParameterNamespace() {
		return parameterNamespace;
	}

	@ManagedAttribute
	public void setParameterNamespace(String parameterNamespace) {
		this.parameterNamespace = parameterNamespace;
	}

	
}
