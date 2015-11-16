package hu.bme.mit.v37zen.sm.datamodel.smartmetering;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SdpRouteAssociation extends BaseAssociation {

	private static final long serialVersionUID = 1L;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sdp_id")
	private ServiceDeliveryPoint serviceDeliveryPoint;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="meter_id")
	private Route route;

	private String sdpMRID;
	private String routeMRID;
	
	public SdpRouteAssociation() {
		super();
		
	}

	public SdpRouteAssociation(Date startDate, String status,
			String sdpMRID, String routeMRID) {
		super(startDate, status);
		this.sdpMRID = sdpMRID;
		this.routeMRID = routeMRID;
	}

	public ServiceDeliveryPoint getServiceDeliveryPoint() {
		return serviceDeliveryPoint;
	}

	public void setServiceDeliveryPoint(ServiceDeliveryPoint serviceDeliveryPoint) {
		this.serviceDeliveryPoint = serviceDeliveryPoint;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getSdpMRID() {
		return sdpMRID;
	}

	public void setSdpMRID(String sdpMRID) {
		this.sdpMRID = sdpMRID;
	}

	public String getRouteMRID() {
		return routeMRID;
	}

	public void setRouteMRID(String routeMRID) {
		this.routeMRID = routeMRID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((route == null) ? 0 : route.hashCode());
		result = prime * result
				+ ((routeMRID == null) ? 0 : routeMRID.hashCode());
		result = prime * result + ((sdpMRID == null) ? 0 : sdpMRID.hashCode());
		result = prime
				* result
				+ ((serviceDeliveryPoint == null) ? 0 : serviceDeliveryPoint
						.hashCode());
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
		SdpRouteAssociation other = (SdpRouteAssociation) obj;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		if (routeMRID == null) {
			if (other.routeMRID != null)
				return false;
		} else if (!routeMRID.equals(other.routeMRID))
			return false;
		if (sdpMRID == null) {
			if (other.sdpMRID != null)
				return false;
		} else if (!sdpMRID.equals(other.sdpMRID))
			return false;
		if (serviceDeliveryPoint == null) {
			if (other.serviceDeliveryPoint != null)
				return false;
		} else if (!serviceDeliveryPoint.equals(other.serviceDeliveryPoint))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SdpRouteAssociation [serviceDeliveryPoint="
				+ serviceDeliveryPoint + ", route=" + route + ", sdpMRID="
				+ sdpMRID + ", routeMRID=" + routeMRID + ", id=" + id
				+ ", startDate=" + startDate + ", status=" + status + "]";
	}
		
}
