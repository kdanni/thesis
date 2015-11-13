package hu.bme.mit.v37zen.sm.jpa.datamodel;

import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.v37zen.sm.jpa.datamodel.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ServiceLocation extends BaseEntity {

	private static final long serialVersionUID = -1456633701167826616L;

	private String addressGeneral;
	
	private String addressLine2;	
	
	private String city;
	
	private String stateOrProvince;
	
	private String country;
	
	private String postalCode;
	
	private String poBox;
	
	private String timeZone;
	
	private String locationType;
	
	private String locationCode;
	
	private String latitude;
	
	private String longitude;
	
	@OneToMany(cascade={CascadeType.ALL})
	List<Parameter> parameters = new ArrayList<Parameter>();
	
	public ServiceLocation() {
		super();
	}

	public ServiceLocation(String mRID) {
		super(mRID);
	}

	public ServiceLocation(String mRID, String addressGeneral, String addressLine2,
			String city, String stateOrProvince, String country,
			String postalCode, String poBox, String timeZone,
			String locationType, String locationCode, String latitude,
			String longitude, List<Parameter> parameters) {
		super(mRID);
		this.addressGeneral = addressGeneral;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.stateOrProvince = stateOrProvince;
		this.country = country;
		this.postalCode = postalCode;
		this.poBox = poBox;
		this.timeZone = timeZone;
		this.locationType = locationType;
		this.locationCode = locationCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.parameters = parameters;
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

	public String getAddressGeneral() {
		return addressGeneral;
	}

	public void setAddressGeneral(String addressGeneral) {
		this.addressGeneral = addressGeneral;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPoBox() {
		return poBox;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
}
