package hu.bme.mit.v37zen.sm.jpa.datamodel;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Contact extends BaseEntity {

	private static final long serialVersionUID = 1304873257066387838L;

	
	private String status;
	
	private String firstName;
	
	private String lastName;
	
	private String middleName;
	
	private String homePhoneNumber;
	
	private String mobilePhoneNumber;
	
	private String emailAddress;
	
	private String secondaryEmailAddress;
	
	private String comments;
	
	@OneToMany(cascade={CascadeType.ALL})
	List<Parameter> parameters = new ArrayList<Parameter>();

	
	public Contact() {
		super();
	}

	public Contact(String mRID) {
		super(mRID);
	}

	public Contact(String mRID, String status, String firstName, String lastName,
			String middleName, String homePhoneNumber,
			String mobilePhoneNumber, String emailAddress,
			String secondaryEmailAddress, String comments,
			List<Parameter> parameters) {
		super(mRID);
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.homePhoneNumber = homePhoneNumber;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.emailAddress = emailAddress;
		this.secondaryEmailAddress = secondaryEmailAddress;
		this.comments = comments;
		this.parameters = parameters;
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


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}


	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}


	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}


	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getSecondaryEmailAddress() {
		return secondaryEmailAddress;
	}


	public void setSecondaryEmailAddress(String secondaryEmailAddress) {
		this.secondaryEmailAddress = secondaryEmailAddress;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "Contact [mRID=" + mRID  
				+ "status=" + status + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName
				+ ", homePhoneNumber=" + homePhoneNumber
				+ ", mobilePhoneNumber=" + mobilePhoneNumber
				+ ", emailAddress=" + emailAddress + ", secondaryEmailAddress="
				+ secondaryEmailAddress + ", comments=" + comments
				+ ", parameters=" + parameters + "]";
	}


}
