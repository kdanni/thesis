package hu.bme.mit.v37zen.sm.datamodel;

public class IdentifiedObject extends MasterResourceId{

	/**
	 * A free text name.
	 */
	private String name;
	/**
	 * A local short name of the instance. Objects that are structured in a
	 * functional naming hierarchy have this name local to each particular level in the
	 * hierarchy. The name shall be unique among objects contained by the same parent.
	 */
	private String localName;
	/**
	 * Objects that are structured in a functional naming hierarchy have a
	 * pathName which contains all the IdentifiedObject.localNames from the object to the
	 * root. The pathName, then, is a concatenation of all these names from the leaf object
	 * up to the root of the containment hierarchy, similar to a file path name. For example, if
	 * node "A" contains node "B" that contains node "C", then the pathName for node "C"
	 * may look like "A.B.C". The type of delimiters used between localNames is not specified
	 * but is a local implementation issue.
	 */
	private String pathName;
	/**
	 * A free text name of the instance. This attribute can also be used for localization.
	 */
	private String aliasName;
	/**
	 * A free format description of the instance.
	 */
	private String description;
	
	//************************
	//	Constructors
	//************************
	public IdentifiedObject() {
		super();
	}
	public IdentifiedObject(String mRID) {
		super(mRID);
	}	
		
	public IdentifiedObject(String mRID, String name, String localName, String pathName,
			String aliasName, String description) {
		super();
		this.name = name;
		this.localName = localName;
		this.pathName = pathName;
		this.aliasName = aliasName;
		this.description = description;
	}	
	
	//************************
	//	Getters and Setters
	//************************
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
}
