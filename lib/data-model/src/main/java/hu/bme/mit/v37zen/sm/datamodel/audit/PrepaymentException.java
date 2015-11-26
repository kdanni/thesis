package hu.bme.mit.v37zen.sm.datamodel.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class PrepaymentException implements Serializable{

    private static final long serialVersionUID = -5229464342058984841L;

	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Basic(optional = false)  
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")  
    protected Long id;  
  
    @Column(name = "version")  
    @Version  
    private Long version;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exceptionTime;
	
	private String message;
	
	private String level = "INFO";
	
	private String type = "Exception";
	
	public PrepaymentException() {
	}
	
	public PrepaymentException(Date exceptionTime,
			String message) {
		super();
		this.exceptionTime = exceptionTime;
		this.message = message;
	}

	public PrepaymentException(Date exceptionTime,
			String message, String level, String type) {
		super();
		this.exceptionTime = exceptionTime;
		this.message = message;
		this.level = level;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exceptionTime == null) ? 0 : exceptionTime.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		PrepaymentException other = (PrepaymentException) obj;
		if (exceptionTime == null) {
			if (other.exceptionTime != null)
				return false;
		} else if (!exceptionTime.equals(other.exceptionTime))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrepaymentException [id=" + id + ", version=" + version
				+ ", exceptionTime=" + exceptionTime + ", message=" + message
				+ ", level=" + level + ", type=" + type + "]";
	}	
}
