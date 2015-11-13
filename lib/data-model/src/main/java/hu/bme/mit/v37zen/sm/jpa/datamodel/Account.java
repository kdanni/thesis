package hu.bme.mit.v37zen.sm.jpa.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Account extends BaseEntity{

	private static final long serialVersionUID = -6658438086633726990L;
	
	private String status;

	private String name;
	
	private String name2;
	
	private String accountType;
	
	private String accountClass;

	private String phoneNumber;		
	
	@OneToMany(cascade={CascadeType.ALL})
	List<Parameter> parameters = new ArrayList<Parameter>();

	public Account() {
		super();
	}

	public Account(String mRID) {
		super(mRID);
	}
	
	public Account(String mRID, String status, String name, String name2,
			String accountType, String accountClass, String phoneNumber) {
		super(mRID);
		this.status = status;
		this.name = name;
		this.name2 = name2;
		this.accountType = accountType;
		this.accountClass = accountClass;
		this.phoneNumber = phoneNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountClass() {
		return accountClass;
	}

	public void setAccountClass(String accountClass) {
		this.accountClass = accountClass;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Account [mRID= "+ mRID + ", status=" + status + ", name=" + name + ", name2="
				+ name2 + ", accountType=" + accountType + ", accountClass="
				+ accountClass + ", phoneNumber=" + phoneNumber + ", parameters=" + parameters + "]";
	}

	
	
	
}
