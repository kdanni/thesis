package hu.bme.mit.v37zen.prepayment.datasync.configurators;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName="bean:name=SyncMessageAssociationProcessorConfigurator",
		description="AssociationProcessorConfigurator.")
public class AssociationProcessorConfigurator extends BaseConfigurator {

	private String accountSdpAssociationSelector;
	private String accountSdpStartDateSelector;
	private String accountSdpEndDateSelector;
	private String accountSdpStatusSelector;
	
	private String sdpMeterAssociationSelector;
	private String sdpMeterStartDateSelector;
	private String sdpMeterEndDateSelector;
	private String sdpMeterStatusSelector;
	
	private String accountContactAssociationSelector;
	private String accountContactStartDateSelector;
	private String accountContactEndDateSelector;
	private String accountContactStatusSelector;
	
	private String sdpServiceLocationAssociationSelector;
	private String sdpServiceLocationStartDateSelector;
	private String sdpServiceLocationEndDateSelector;
	private String sdpServiceLocationStatusSelector;
	
	private String sdpRouteAssociationSelector;
	private String sdpRouteStartDateSelector;
	private String sdpRouteEndDateSelector;
	private String sdpRouteStatusSelector;
		
	private String accountIdSelector;
	private String sdpIdSelector;
	private String meterIdSelector;
	private String contactIdSelector;
	private String serviceLocationIdSelector;
	private String routeIdSelector;
		
	@ManagedAttribute
	public String getAccountIdSelector() {
		return accountIdSelector;
	}
	
	@ManagedAttribute
	public void setAccountIdSelector(String accountIdSelector) {
		this.accountIdSelector = accountIdSelector;
	}
	
	@ManagedAttribute
	public String getSdpIdSelector() {
		return sdpIdSelector;
	}
	
	@ManagedAttribute
	public void setSdpIdSelector(String sdpIdSelector) {
		this.sdpIdSelector = sdpIdSelector;
	}

	@ManagedAttribute
	public String getMeterIdSelector() {
		return meterIdSelector;
	}

	@ManagedAttribute
	public void setMeterIdSelector(String meterIdSelector) {
		this.meterIdSelector = meterIdSelector;
	}

	@ManagedAttribute
	public String getAccountSdpAssociationSelector() {
		return accountSdpAssociationSelector;
	}

	@ManagedAttribute
	public void setAccountSdpAssociationSelector(
			String accountSdpAssociationSelector) {
		this.accountSdpAssociationSelector = accountSdpAssociationSelector;
	}

	@ManagedAttribute
	public String getAccountSdpStartDateSelector() {
		return accountSdpStartDateSelector;
	}

	@ManagedAttribute
	public void setAccountSdpStartDateSelector(String accountSdpStartDateSelector) {
		this.accountSdpStartDateSelector = accountSdpStartDateSelector;
	}

	@ManagedAttribute
	public String getAccountSdpStatusSelector() {
		return accountSdpStatusSelector;
	}

	@ManagedAttribute
	public void setAccountSdpStatusSelector(String accountSdpStatusSelector) {
		this.accountSdpStatusSelector = accountSdpStatusSelector;
	}

	@ManagedAttribute
	public String getSdpMeterAssociationSelector() {
		return sdpMeterAssociationSelector;
	}

	@ManagedAttribute
	public void setSdpMeterAssociationSelector(String sdpMeterAssociationSelector) {
		this.sdpMeterAssociationSelector = sdpMeterAssociationSelector;
	}

	@ManagedAttribute
	public String getSdpMeterStartDateSelector() {
		return sdpMeterStartDateSelector;
	}

	@ManagedAttribute
	public void setSdpMeterStartDateSelector(String sdpMeterStartDateSelector) {
		this.sdpMeterStartDateSelector = sdpMeterStartDateSelector;
	}

	@ManagedAttribute
	public String getSdpMeterStatusSelector() {
		return sdpMeterStatusSelector;
	}

	@ManagedAttribute
	public void setSdpMeterStatusSelector(String sdpMeterStatusSelector) {
		this.sdpMeterStatusSelector = sdpMeterStatusSelector;
	}

	@ManagedAttribute
	public String getServiceLocationIdSelector() {
		return serviceLocationIdSelector;
	}

	@ManagedAttribute
	public void setServiceLocationIdSelector(String serviceLocationIdSelector) {
		this.serviceLocationIdSelector = serviceLocationIdSelector;
	}

	@ManagedAttribute
	public String getContactIdSelector() {
		return contactIdSelector;
	}

	@ManagedAttribute
	public void setContactIdSelector(String contactIdSelector) {
		this.contactIdSelector = contactIdSelector;
	}

	@ManagedAttribute
	public String getAccountContactAssociationSelector() {
		return accountContactAssociationSelector;
	}

	@ManagedAttribute
	public void setAccountContactAssociationSelector(
			String accountContactAssociationSelector) {
		this.accountContactAssociationSelector = accountContactAssociationSelector;
	}

	@ManagedAttribute
	public String getAccountContactStartDateSelector() {
		return accountContactStartDateSelector;
	}

	@ManagedAttribute
	public void setAccountContactStartDateSelector(
			String accountContactStartDateSelector) {
		this.accountContactStartDateSelector = accountContactStartDateSelector;
	}

	@ManagedAttribute
	public String getAccountContactStatusSelector() {
		return accountContactStatusSelector;
	}

	@ManagedAttribute
	public void setAccountContactStatusSelector(String accountContactStatusSelector) {
		this.accountContactStatusSelector = accountContactStatusSelector;
	}

	@ManagedAttribute
	public String getSdpServiceLocationAssociationSelector() {
		return sdpServiceLocationAssociationSelector;
	}

	@ManagedAttribute
	public void setSdpServiceLocationAssociationSelector(
			String sdpServiceLocationAssociationSelector) {
		this.sdpServiceLocationAssociationSelector = sdpServiceLocationAssociationSelector;
	}

	@ManagedAttribute
	public String getSdpServiceLocationStartDateSelector() {
		return sdpServiceLocationStartDateSelector;
	}

	@ManagedAttribute
	public void setSdpServiceLocationStartDateSelector(
			String sdpServiceLocationStartDateSelector) {
		this.sdpServiceLocationStartDateSelector = sdpServiceLocationStartDateSelector;
	}

	@ManagedAttribute
	public String getSdpServiceLocationStatusSelector() {
		return sdpServiceLocationStatusSelector;
	}

	@ManagedAttribute
	public void setSdpServiceLocationStatusSelector(
			String sdpServiceLocationStatusSelector) {
		this.sdpServiceLocationStatusSelector = sdpServiceLocationStatusSelector;
	}

	@ManagedAttribute
	public String getAccountSdpEndDateSelector() {
		return accountSdpEndDateSelector;
	}

	@ManagedAttribute
	public void setAccountSdpEndDateSelector(String accountSdpEndDateSelector) {
		this.accountSdpEndDateSelector = accountSdpEndDateSelector;
	}

	@ManagedAttribute
	public String getSdpMeterEndDateSelector() {
		return sdpMeterEndDateSelector;
	}

	@ManagedAttribute
	public void setSdpMeterEndDateSelector(String sdpMeterEndDateSelector) {
		this.sdpMeterEndDateSelector = sdpMeterEndDateSelector;
	}

	@ManagedAttribute
	public String getAccountContactEndDateSelector() {
		return accountContactEndDateSelector;
	}

	@ManagedAttribute
	public void setAccountContactEndDateSelector(
			String accountContactEndDateSelector) {
		this.accountContactEndDateSelector = accountContactEndDateSelector;
	}

	@ManagedAttribute
	public String getSdpServiceLocationEndDateSelector() {
		return sdpServiceLocationEndDateSelector;
	}

	@ManagedAttribute
	public void setSdpServiceLocationEndDateSelector(
			String sdpServiceLocationEndDateSelector) {
		this.sdpServiceLocationEndDateSelector = sdpServiceLocationEndDateSelector;
	}

	@ManagedAttribute
	public String getSdpRouteAssociationSelector() {
		return sdpRouteAssociationSelector;
	}

	@ManagedAttribute
	public void setSdpRouteAssociationSelector(
			String sdpRouteAssociationSelector) {
		this.sdpRouteAssociationSelector = sdpRouteAssociationSelector;
	}

	@ManagedAttribute
	public String getSdpRouteStartDateSelector() {
		return sdpRouteStartDateSelector;
	}

	@ManagedAttribute
	public void setSdpRouteStartDateSelector(String sdpRouteStartDateSelector) {
		this.sdpRouteStartDateSelector = sdpRouteStartDateSelector;
	}

	@ManagedAttribute
	public String getSdpRouteEndDateSelector() {
		return sdpRouteEndDateSelector;
	}

	@ManagedAttribute
	public void setSdpRouteEndDateSelector(String sdpRouteEndDateSelector) {
		this.sdpRouteEndDateSelector = sdpRouteEndDateSelector;
	}

	@ManagedAttribute
	public String getSdpRouteStatusSelector() {
		return sdpRouteStatusSelector;
	}

	@ManagedAttribute
	public void setSdpRouteStatusSelector(String sdpRouteStatusSelector) {
		this.sdpRouteStatusSelector = sdpRouteStatusSelector;
	}

	@ManagedAttribute
	public String getRouteIdSelector() {
		return routeIdSelector;
	}

	@ManagedAttribute
	public void setRouteIdSelector(String routeIdSelector) {
		this.routeIdSelector = routeIdSelector;
	}
}
