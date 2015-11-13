package hu.bme.mit.v37zen.sm.jpa.datamodel;
import java.io.Serializable;  

import javax.persistence.Basic;  
import javax.persistence.Column;  
import javax.persistence.GeneratedValue;  
import javax.persistence.GenerationType;  
import javax.persistence.Id;  
import javax.persistence.MappedSuperclass;  
import javax.persistence.Version;  
  
@MappedSuperclass  
public abstract class BaseEntity implements Serializable {  
  
    private static final long serialVersionUID = 1L;  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    @Basic(optional = false)  
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")  
    protected Long id;  
  
    @Column(name = "version")  
    @Version  
    private Long version;
    
    @Column(name = "mRID")
    protected String mRID;
    

    public BaseEntity() {
		super();
	}
    
	public BaseEntity(String mRID) {
		super();
		this.mRID = mRID;
	}
  
    public Long getId() {  
        return id;  
    }  
  
    public Long getVersion() {  
        return version;  
    }  
  
    
    public String getMRID() {
		return mRID;
	}

	public void setMRID(String mRID) {
		this.mRID = mRID;
	}
	  
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mRID == null) ? 0 : mRID.hashCode());
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
		BaseEntity other = (BaseEntity) obj;
		if (mRID == null) {
			if (other.mRID != null)
				return false;
		} else if (!mRID.equals(other.mRID))
			return false;
		return true;
	}

	@Override  
    public String toString() {  
        return this.getClass().getName() + " [ID=" + id + "]";  
    }  
}  