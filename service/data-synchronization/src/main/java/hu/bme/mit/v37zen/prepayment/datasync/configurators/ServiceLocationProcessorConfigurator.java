package hu.bme.mit.v37zen.prepayment.datasync.configurators;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(
        objectName="bean:name=SyncMessageServiceLocationProcessorConfigurator",
        description="ServiceLocationProcessorConfigurator")
public class ServiceLocationProcessorConfigurator extends BaseConfigurator {

	private String ServiceLocationSelector;

	private String parameterNamespace;
	
	private String mridSelector;
	
	private String addressGeneralSelector;
	
	private String addressLine2Selector;	
	
	private String citySelector;
	
	private String stateOrProvinceSelector;
	
	private String countrySelector;
	
	private String postalCodeSelector;
	
	private String poBoxSelector;
	
	private String timeZoneSelector;
	
	private String locationTypeSelector;
	
	private String locationCodeSelector;
	
	private String latitudeSelector;
	
	private String longitudeSelector;

	
	@ManagedAttribute
	public String getServiceLocationSelector() {
		return ServiceLocationSelector;
	}

	@ManagedAttribute
	public void setServiceLocationSelector(String serviceLocationSelector) {
		ServiceLocationSelector = serviceLocationSelector;
	}

	@ManagedAttribute
	public String getParameterNamespace() {
		return parameterNamespace;
	}

	@ManagedAttribute
	public void setParameterNamespace(String parameterNamespace) {
		this.parameterNamespace = parameterNamespace;
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
	public String getAddressGeneralSelector() {
		return addressGeneralSelector;
	}

	@ManagedAttribute
	public void setAddressGeneralSelector(String addressGeneralSelector) {
		this.addressGeneralSelector = addressGeneralSelector;
	}

	@ManagedAttribute
	public String getAddressLine2Selector() {
		return addressLine2Selector;
	}

	@ManagedAttribute
	public void setAddressLine2Selector(String addressLine2Selector) {
		this.addressLine2Selector = addressLine2Selector;
	}

	@ManagedAttribute
	public String getCitySelector() {
		return citySelector;
	}

	@ManagedAttribute
	public void setCitySelector(String citySelector) {
		this.citySelector = citySelector;
	}

	@ManagedAttribute
	public String getStateOrProvinceSelector() {
		return stateOrProvinceSelector;
	}

	@ManagedAttribute
	public void setStateOrProvinceSelector(String stateOrProvinceSelector) {
		this.stateOrProvinceSelector = stateOrProvinceSelector;
	}

	@ManagedAttribute
	public String getCountrySelector() {
		return countrySelector;
	}

	@ManagedAttribute
	public void setCountrySelector(String countrySelector) {
		this.countrySelector = countrySelector;
	}

	@ManagedAttribute
	public String getPostalCodeSelector() {
		return postalCodeSelector;
	}

	@ManagedAttribute
	public void setPostalCodeSelector(String postalCodeSelector) {
		this.postalCodeSelector = postalCodeSelector;
	}

	@ManagedAttribute
	public String getPoBoxSelector() {
		return poBoxSelector;
	}

	@ManagedAttribute
	public void setPoBoxSelector(String poBoxSelector) {
		this.poBoxSelector = poBoxSelector;
	}

	@ManagedAttribute
	public String getTimeZoneSelector() {
		return timeZoneSelector;
	}

	@ManagedAttribute
	public void setTimeZoneSelector(String timeZoneSelector) {
		this.timeZoneSelector = timeZoneSelector;
	}

	@ManagedAttribute
	public String getLocationTypeSelector() {
		return locationTypeSelector;
	}

	@ManagedAttribute
	public void setLocationTypeSelector(String locationTypeSelector) {
		this.locationTypeSelector = locationTypeSelector;
	}

	@ManagedAttribute
	public String getLocationCodeSelector() {
		return locationCodeSelector;
	}

	@ManagedAttribute
	public void setLocationCodeSelector(String locationCodeSelector) {
		this.locationCodeSelector = locationCodeSelector;
	}

	@ManagedAttribute
	public String getLatitudeSelector() {
		return latitudeSelector;
	}

	@ManagedAttribute
	public void setLatitudeSelector(String latitudeSelector) {
		this.latitudeSelector = latitudeSelector;
	}

	@ManagedAttribute
	public String getLongitudeSelector() {
		return longitudeSelector;
	}

	@ManagedAttribute
	public void setLongitudeSelector(String longitudeSelector) {
		this.longitudeSelector = longitudeSelector;
	}
	
	
}
