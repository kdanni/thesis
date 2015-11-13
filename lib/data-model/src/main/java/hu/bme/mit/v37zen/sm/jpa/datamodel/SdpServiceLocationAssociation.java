package hu.bme.mit.v37zen.sm.jpa.datamodel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SdpServiceLocationAssociation extends BaseAssociation {

	private static final long serialVersionUID = 1832678754382709283L;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sdp_id")
	private ServiceDeliveryPoint serviceDeliveryPoint;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="meter_id")
	private ServiceLocation serviceLocation;

	private String sdpMRID;
	private String serviceLocationMRID;
	
	
	public SdpServiceLocationAssociation() {
		super();
		
	}

	public SdpServiceLocationAssociation(Date startDate, String status,
			String sdpMRID, String serviceLocationMRID) {
		super(startDate, status);
		this.sdpMRID = sdpMRID;
		this.serviceLocationMRID = serviceLocationMRID;
	}

	public ServiceDeliveryPoint getServiceDeliveryPoint() {
		return serviceDeliveryPoint;
	}

	public void setServiceDeliveryPoint(ServiceDeliveryPoint serviceDeliveryPoint) {
		this.serviceDeliveryPoint = serviceDeliveryPoint;
	}

	public ServiceLocation getServiceLocation() {
		return serviceLocation;
	}

	public void setServiceLocation(ServiceLocation serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public String getSdpMRID() {
		return sdpMRID;
	}

	public void setSdpMRID(String sdpMRID) {
		this.sdpMRID = sdpMRID;
	}

	public String getServiceLocationMRID() {
		return serviceLocationMRID;
	}

	public void setServiceLocationMRID(String serviceLocationMRID) {
		this.serviceLocationMRID = serviceLocationMRID;
	}


	@Override
	public String toString() {
		return "SdpMeterAssociation [sdpMRID=" + sdpMRID + ", serviceLocationMRID="
				+ serviceLocationMRID + ", id=" + id + ", startDate=" + startDate
				+ ", status=" + status + "]";
	}
}
