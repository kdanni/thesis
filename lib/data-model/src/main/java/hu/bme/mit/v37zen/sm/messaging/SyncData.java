package hu.bme.mit.v37zen.sm.messaging;

import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountContactAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Contact;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Route;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpRouteAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpServiceLocationAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceLocation;

import java.io.Serializable;
import java.util.List;

public class SyncData implements Serializable {
	
	private static final long serialVersionUID = 8686170581398417587L;
	
	private List<Account> accounts;
	private List<ServiceDeliveryPoint> serviceDeliveryPoints;
	private List<MeterAsset> meterAssets;
	private List<Contact> contacts;
	private List<ServiceLocation> serviceLocations;
	private List<Route> routes;
	private List<SdpMeterAssociation> sdpMeterAssociations;
	private List<AccountSDPAssociation> accountSDPAssociations;
	private List<SdpServiceLocationAssociation> sdpServiceLocationAssociations;
	private List<AccountContactAssociation> accountContactAssociations;
	private List<SdpRouteAssociation> sdpRouteAssociations;
	
	public SyncData(List<Account> accounts,
			List<ServiceDeliveryPoint> serviceDeliveryPoints,
			List<MeterAsset> meterAssets, List<Contact> contacts,
			List<ServiceLocation> serviceLocations, List<Route> routes,
			List<SdpMeterAssociation> sdpMeterAssociations,
			List<AccountSDPAssociation> accountSDPAssociations,
			List<SdpServiceLocationAssociation> sdpServiceLocationAssociations,
			List<AccountContactAssociation> accountContactAssociations,
			List<SdpRouteAssociation> sdpRouteAssociations) {
		super();
		this.accounts = accounts;
		this.serviceDeliveryPoints = serviceDeliveryPoints;
		this.meterAssets = meterAssets;
		this.contacts = contacts;
		this.serviceLocations = serviceLocations;
		this.routes = routes;
		this.sdpMeterAssociations = sdpMeterAssociations;
		this.accountSDPAssociations = accountSDPAssociations;
		this.sdpServiceLocationAssociations = sdpServiceLocationAssociations;
		this.accountContactAssociations = accountContactAssociations;
		this.sdpRouteAssociations = sdpRouteAssociations;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public List<ServiceDeliveryPoint> getServiceDeliveryPoints() {
		return serviceDeliveryPoints;
	}
	public void setServiceDeliveryPoints(
			List<ServiceDeliveryPoint> serviceDeliveryPoints) {
		this.serviceDeliveryPoints = serviceDeliveryPoints;
	}
	public List<MeterAsset> getMeterAssets() {
		return meterAssets;
	}
	public void setMeterAssets(List<MeterAsset> meterAssets) {
		this.meterAssets = meterAssets;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public List<ServiceLocation> getServiceLocations() {
		return serviceLocations;
	}
	public void setServiceLocations(List<ServiceLocation> serviceLocations) {
		this.serviceLocations = serviceLocations;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	public List<SdpMeterAssociation> getSdpMeterAssociations() {
		return sdpMeterAssociations;
	}
	public void setSdpMeterAssociations(
			List<SdpMeterAssociation> sdpMeterAssociations) {
		this.sdpMeterAssociations = sdpMeterAssociations;
	}
	public List<AccountSDPAssociation> getAccountSDPAssociations() {
		return accountSDPAssociations;
	}
	public void setAccountSDPAssociations(
			List<AccountSDPAssociation> accountSDPAssociations) {
		this.accountSDPAssociations = accountSDPAssociations;
	}
	public List<SdpServiceLocationAssociation> getSdpServiceLocationAssociations() {
		return sdpServiceLocationAssociations;
	}
	public void setSdpServiceLocationAssociations(
			List<SdpServiceLocationAssociation> sdpServiceLocationAssociations) {
		this.sdpServiceLocationAssociations = sdpServiceLocationAssociations;
	}
	public List<AccountContactAssociation> getAccountContactAssociations() {
		return accountContactAssociations;
	}
	public void setAccountContactAssociations(
			List<AccountContactAssociation> accountContactAssociations) {
		this.accountContactAssociations = accountContactAssociations;
	}
	public List<SdpRouteAssociation> getSdpRouteAssociations() {
		return sdpRouteAssociations;
	}
	public void setSdpRouteAssociations(
			List<SdpRouteAssociation> sdpRouteAssociations) {
		this.sdpRouteAssociations = sdpRouteAssociations;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((accountContactAssociations == null) ? 0
						: accountContactAssociations.hashCode());
		result = prime
				* result
				+ ((accountSDPAssociations == null) ? 0
						: accountSDPAssociations.hashCode());
		result = prime * result
				+ ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result
				+ ((contacts == null) ? 0 : contacts.hashCode());
		result = prime * result
				+ ((meterAssets == null) ? 0 : meterAssets.hashCode());
		result = prime * result + ((routes == null) ? 0 : routes.hashCode());
		result = prime
				* result
				+ ((sdpMeterAssociations == null) ? 0 : sdpMeterAssociations
						.hashCode());
		result = prime
				* result
				+ ((sdpRouteAssociations == null) ? 0 : sdpRouteAssociations
						.hashCode());
		result = prime
				* result
				+ ((sdpServiceLocationAssociations == null) ? 0
						: sdpServiceLocationAssociations.hashCode());
		result = prime
				* result
				+ ((serviceDeliveryPoints == null) ? 0 : serviceDeliveryPoints
						.hashCode());
		result = prime
				* result
				+ ((serviceLocations == null) ? 0 : serviceLocations.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SyncData other = (SyncData) obj;
		if (accountContactAssociations == null) {
			if (other.accountContactAssociations != null)
				return false;
		} else if (!accountContactAssociations
				.equals(other.accountContactAssociations))
			return false;
		if (accountSDPAssociations == null) {
			if (other.accountSDPAssociations != null)
				return false;
		} else if (!accountSDPAssociations.equals(other.accountSDPAssociations))
			return false;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (contacts == null) {
			if (other.contacts != null)
				return false;
		} else if (!contacts.equals(other.contacts))
			return false;
		if (meterAssets == null) {
			if (other.meterAssets != null)
				return false;
		} else if (!meterAssets.equals(other.meterAssets))
			return false;
		if (routes == null) {
			if (other.routes != null)
				return false;
		} else if (!routes.equals(other.routes))
			return false;
		if (sdpMeterAssociations == null) {
			if (other.sdpMeterAssociations != null)
				return false;
		} else if (!sdpMeterAssociations.equals(other.sdpMeterAssociations))
			return false;
		if (sdpRouteAssociations == null) {
			if (other.sdpRouteAssociations != null)
				return false;
		} else if (!sdpRouteAssociations.equals(other.sdpRouteAssociations))
			return false;
		if (sdpServiceLocationAssociations == null) {
			if (other.sdpServiceLocationAssociations != null)
				return false;
		} else if (!sdpServiceLocationAssociations
				.equals(other.sdpServiceLocationAssociations))
			return false;
		if (serviceDeliveryPoints == null) {
			if (other.serviceDeliveryPoints != null)
				return false;
		} else if (!serviceDeliveryPoints.equals(other.serviceDeliveryPoints))
			return false;
		if (serviceLocations == null) {
			if (other.serviceLocations != null)
				return false;
		} else if (!serviceLocations.equals(other.serviceLocations))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SyncData [accounts=" + accounts + ", serviceDeliveryPoints="
				+ serviceDeliveryPoints + ", meterAssets=" + meterAssets
				+ ", contacts=" + contacts + ", serviceLocations="
				+ serviceLocations + ", routes=" + routes
				+ ", sdpMeterAssociations=" + sdpMeterAssociations
				+ ", accountSDPAssociations=" + accountSDPAssociations
				+ ", sdpServiceLocationAssociations="
				+ sdpServiceLocationAssociations
				+ ", accountContactAssociations=" + accountContactAssociations
				+ ", sdpRouteAssociations=" + sdpRouteAssociations + "]";
	}
}
