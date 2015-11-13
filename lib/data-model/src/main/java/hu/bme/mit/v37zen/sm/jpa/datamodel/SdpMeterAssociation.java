package hu.bme.mit.v37zen.sm.jpa.datamodel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SdpMeterAssociation extends BaseAssociation {

	private static final long serialVersionUID = 5763647574562927222L;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sdp_id")
	private ServiceDeliveryPoint serviceDeliveryPoint;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="meter_id")
	private MeterAsset meterAsset;

	private String sdpMRID;
	private String meterAssetMRID;
	
	public SdpMeterAssociation() {
		super();
	}

	public SdpMeterAssociation(Date startDate, String status) {
		super(startDate, status);
	}
	
	

	public SdpMeterAssociation(ServiceDeliveryPoint sdpDeliveryPoint,
			MeterAsset meterAsset) {
		super();
		this.setServiceDeliveryPoint(sdpDeliveryPoint);
		this.setMeterAsset(meterAsset);
	}
	
	public SdpMeterAssociation(ServiceDeliveryPoint sdpDeliveryPoint,
			MeterAsset meterAsset, Date startDate, String status) {
		super(startDate, status);
		this.setServiceDeliveryPoint(sdpDeliveryPoint);
		this.setMeterAsset(meterAsset);
	}

	public ServiceDeliveryPoint getServiceDeliveryPoint() {
		return serviceDeliveryPoint;
	}

	public void setServiceDeliveryPoint(ServiceDeliveryPoint serviceDeliveryPoint) {
		this.sdpMRID = serviceDeliveryPoint.getMRID();
		this.serviceDeliveryPoint = serviceDeliveryPoint;
	}

	public MeterAsset getMeterAsset() {
		return meterAsset;
	}

	public void setMeterAsset(MeterAsset meterAsset) {
		this.meterAssetMRID = meterAsset.getMRID();
		this.meterAsset = meterAsset;
	}

	public String getSdpMRID() {
		return sdpMRID;
	}

	public void setSdpMRID(String sdpMRID) {
		this.sdpMRID = sdpMRID;
	}

	public String getMeterAssetMRID() {
		return meterAssetMRID;
	}

	public void setMeterAssetMRID(String meterAssetMRID) {
		this.meterAssetMRID = meterAssetMRID;
	}

	@Override
	public String toString() {
		return "SdpMeterAssociation [sdpMRID=" + sdpMRID + ", meterAssetMRID="
				+ meterAssetMRID + ", id=" + id + ", startDate=" + startDate
				+ ", status=" + status + "]";
	}


	
	
}
