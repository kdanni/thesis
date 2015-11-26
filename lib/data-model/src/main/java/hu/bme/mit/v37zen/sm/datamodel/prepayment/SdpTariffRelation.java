package hu.bme.mit.v37zen.sm.datamodel.prepayment;

import hu.bme.mit.v37zen.sm.datamodel.BaseAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class SdpTariffRelation extends BaseAssociation {

	private static final long serialVersionUID = -8216971786866716422L;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sdp_id")
	private ServiceDeliveryPoint serviceDeliveryPoint;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tarif_id")
	private Tariff account;
	
	private String sdpMRID;
	private String tarifMRID;
	
	public SdpTariffRelation() {
		super();
	}
		
	public SdpTariffRelation(Date startDate, String status, String sdpMRID,
			String tarifMRID) {
		super(startDate, status);
		this.sdpMRID = sdpMRID;
		this.tarifMRID = tarifMRID;
	}

	public ServiceDeliveryPoint getServiceDeliveryPoint() {
		return serviceDeliveryPoint;
	}
	public void setServiceDeliveryPoint(ServiceDeliveryPoint serviceDeliveryPoint) {
		this.serviceDeliveryPoint = serviceDeliveryPoint;
	}
	public Tariff getAccount() {
		return account;
	}
	public void setAccount(Tariff account) {
		this.account = account;
	}
	public String getSdpMRID() {
		return sdpMRID;
	}
	public void setSdpMRID(String sdpMRID) {
		this.sdpMRID = sdpMRID;
	}
	public String getTarifMRID() {
		return tarifMRID;
	}
	public void setTarifMRID(String tarifMRID) {
		this.tarifMRID = tarifMRID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((sdpMRID == null) ? 0 : sdpMRID.hashCode());
		result = prime
				* result
				+ ((serviceDeliveryPoint == null) ? 0 : serviceDeliveryPoint
						.hashCode());
		result = prime * result
				+ ((tarifMRID == null) ? 0 : tarifMRID.hashCode());
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
		SdpTariffRelation other = (SdpTariffRelation) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
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
		if (tarifMRID == null) {
			if (other.tarifMRID != null)
				return false;
		} else if (!tarifMRID.equals(other.tarifMRID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SdpTariffRelation [serviceDeliveryPoint="
				+ serviceDeliveryPoint + ", account=" + account + ", sdpMRID="
				+ sdpMRID + ", tarifMRID=" + tarifMRID + ", id=" + id
				+ ", startDate=" + startDate + ", status=" + status + "]";
	}
	
	
}
