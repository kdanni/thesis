package hu.bme.mit.v37zen.prepayment.datasync.configurators;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(
        objectName="bean:name=SyncMessageContactProcessorConfigurator",
        description="ContactProcessorConfigurator")
public class ContactProcessorConfigurator extends BaseConfigurator {

	private String contactSelector;

	private String parameterNamespace;

	private String mridSelector;
	
	private String statusSelector;
	
	private String firstNameSelector;
	
	private String lastNameSelector;
	
	private String middleNameSelector;
	
	private String homePhoneNumberSelector;
	
	private String mobilePhoneNumberSelector;
	
	private String emailAddressSelector;
	
	private String secondaryEmailAddressSelector;
	
	private String commentsSelector;

	
	@ManagedAttribute
	public String getContactSelector() {
		return contactSelector;
	}

	@ManagedAttribute
	public void setContactSelector(String contactSelector) {
		this.contactSelector = contactSelector;
	}

	@ManagedAttribute
	public String getParameterNamespace() {
		return parameterNamespace;
	}

	@ManagedAttribute
	public void setParameterNamespace(String parameterNamespace) {
		this.parameterNamespace = parameterNamespace;
	}

	@ManagedAttribute
	public String getMridSelector() {
		return mridSelector;
	}

	@ManagedAttribute
	public void setMridSelector(String mridSelector) {
		this.mridSelector = mridSelector;
	}

	@ManagedAttribute
	public String getStatusSelector() {
		return statusSelector;
	}

	@ManagedAttribute
	public void setStatusSelector(String statusSelector) {
		this.statusSelector = statusSelector;
	}

	@ManagedAttribute
	public String getFirstNameSelector() {
		return firstNameSelector;
	}

	@ManagedAttribute
	public void setFirstNameSelector(String firstNameSelector) {
		this.firstNameSelector = firstNameSelector;
	}

	@ManagedAttribute
	public String getLastNameSelector() {
		return lastNameSelector;
	}

	@ManagedAttribute
	public void setLastNameSelector(String lastNameSelector) {
		this.lastNameSelector = lastNameSelector;
	}

	@ManagedAttribute
	public String getMiddleNameSelector() {
		return middleNameSelector;
	}

	@ManagedAttribute
	public void setMiddleNameSelector(String middleNameSelector) {
		this.middleNameSelector = middleNameSelector;
	}

	@ManagedAttribute
	public String getHomePhoneNumberSelector() {
		return homePhoneNumberSelector;
	}

	@ManagedAttribute
	public void setHomePhoneNumberSelector(String homePhoneNumberSelector) {
		this.homePhoneNumberSelector = homePhoneNumberSelector;
	}

	@ManagedAttribute
	public String getMobilePhoneNumberSelector() {
		return mobilePhoneNumberSelector;
	}

	@ManagedAttribute
	public void setMobilePhoneNumberSelector(String mobilePhoneNumberSelector) {
		this.mobilePhoneNumberSelector = mobilePhoneNumberSelector;
	}

	@ManagedAttribute
	public String getEmailAddressSelector() {
		return emailAddressSelector;
	}

	@ManagedAttribute
	public void setEmailAddressSelector(String emailAddressSelector) {
		this.emailAddressSelector = emailAddressSelector;
	}

	@ManagedAttribute
	public String getSecondaryEmailAddressSelector() {
		return secondaryEmailAddressSelector;
	}

	@ManagedAttribute
	public void setSecondaryEmailAddressSelector(
			String secondaryEmailAddressSelector) {
		this.secondaryEmailAddressSelector = secondaryEmailAddressSelector;
	}

	@ManagedAttribute
	public String getCommentsSelector() {
		return commentsSelector;
	}

	@ManagedAttribute
	public void setCommentsSelector(String commentsSelector) {
		this.commentsSelector = commentsSelector;
	}


	
	
	
}
