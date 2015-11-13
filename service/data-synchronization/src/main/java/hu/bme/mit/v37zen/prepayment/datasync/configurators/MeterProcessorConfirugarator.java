package hu.bme.mit.v37zen.prepayment.datasync.configurators;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(
        objectName="bean:name=SyncMessageMeterProcessorConfirugarator",
        description="MeterProcessorConfirugarator")
public class MeterProcessorConfirugarator extends BaseConfigurator {
	
	private String meterAssetSelector;
	
	private String mridSelector;
	private String statusSelector;
	private String manufacturedDateSelector;
	private String virtualIndSelector;
	private String serviceTypeSelector;
	private String serialNumberSelector;
	private String installDateSelector;
	private String firstReadSelector;
	private String lastReadSelector;
	
	private String parameterNamespace;
	
	/**
	 * @return the meterAssetSelector
	 */
	@ManagedAttribute
	public String getMeterAssetSelector() {
		return meterAssetSelector;
	}

	/**
	 * @param meterAssetSelector the meterAssetSelector to set
	 */
	@ManagedAttribute
	public void setMeterAssetSelector(String meterAssetSelector) {
		this.meterAssetSelector = meterAssetSelector;
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
	public String getStatusSelector() {
		return statusSelector;
	}
	
	@ManagedAttribute
	public void setStatusSelector(String statusSelector) {
		this.statusSelector = statusSelector;
	}
	
	@ManagedAttribute
	public String getManufacturedDateSelector() {
		return manufacturedDateSelector;
	}
	
	@ManagedAttribute
	public void setManufacturedDateSelector(String manufacturedDateSelector) {
		this.manufacturedDateSelector = manufacturedDateSelector;
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
	public String getSerialNumberSelector() {
		return serialNumberSelector;
	}
	
	@ManagedAttribute
	public void setSerialNumberSelector(String serialNumberSelector) {
		this.serialNumberSelector = serialNumberSelector;
	}
	
	@ManagedAttribute
	public String getInstallDateSelector() {
		return installDateSelector;
	}
	
	@ManagedAttribute
	public void setInstallDateSelector(String installDateSelector) {
		this.installDateSelector = installDateSelector;
	}
	
	@ManagedAttribute
	public String getFirstReadSelector() {
		return firstReadSelector;
	}
	
	@ManagedAttribute
	public void setFirstReadSelector(String firstReadSelector) {
		this.firstReadSelector = firstReadSelector;
	}
	
	@ManagedAttribute
	public String getLastReadSelector() {
		return lastReadSelector;
	}
	
	@ManagedAttribute
	public void setLastReadSelector(String lastReadSelector) {
		this.lastReadSelector = lastReadSelector;
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
