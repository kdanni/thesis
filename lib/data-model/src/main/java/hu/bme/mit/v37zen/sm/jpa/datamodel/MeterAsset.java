package hu.bme.mit.v37zen.sm.jpa.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MeterAsset extends BaseEntity {

	private static final long serialVersionUID = -8764900526122408786L;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date manufacturedDate;
	
	private String status;
	
	private String virtualInd;
	
	private String serviceType;
	
	private String serialNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date installDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstRead;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastRead;

	@OneToMany(cascade={CascadeType.ALL})
	List<Parameter> parameters = new ArrayList<Parameter>();
	
	public MeterAsset(){
		super();
	}

	public MeterAsset(String mRID){
		super(mRID);
	}
		

	public MeterAsset(String mRID, Date manufacturedDate, String status,
			String virtualInd, String serviceType, Parameter parameter) {
		super(mRID);
		this.manufacturedDate = manufacturedDate;
		this.status = status;
		this.virtualInd = virtualInd;
		this.serviceType = serviceType;
		this.parameters.add(parameter);
	}

	public Date getManufacturedDate() {
		return manufacturedDate;
	}

	public void setManufacturedDate(Date manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVirtualInd() {
		return virtualInd;
	}

	public void setVirtualInd(String virtualInd) {
		this.virtualInd = virtualInd;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public Date getFirstRead() {
		return firstRead;
	}

	public void setFirstRead(Date firstRead) {
		this.firstRead = firstRead;
	}

	public Date getLastRead() {
		return lastRead;
	}

	public void setLastRead(Date lastRead) {
		this.lastRead = lastRead;
	}

	@Override
	public String toString() {
		return "MeterAsset [mRID=" + mRID 
				+ "manufacturedDate=" + manufacturedDate + ", status="
				+ status + ", virtualInd=" + virtualInd + ", serviceType="
				+ serviceType + ", serialNumber=" + serialNumber
				+ ", installDate=" + installDate + ", firstRead=" + firstRead
				+ ", lastRead=" + lastRead + ", parameters=" + parameters
				+  "]";
	}
	
	
	
}
