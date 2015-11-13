package hu.bme.mit.v37zen.sm.datamodel;

public class MasterResourceId {
	
	/**
	 * (Master Resource ID): A globally unique machine-readable identifier for an object instance.
	 */
	private String mRID;
	
	public MasterResourceId() {
		super();
	}

	public MasterResourceId(String mRID) {
		super();
		this.mRID = mRID;
	}

	public String getmRID() {
		return mRID;
	}

	public void setmRID(String mRID) {
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
		MasterResourceId other = (MasterResourceId) obj;
		if (mRID == null) {
			if (other.mRID != null)
				return false;
		} else if (!mRID.equals(other.mRID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MasterResourceId [mRID=" + mRID + "]";
	}		
	
	
	
}
