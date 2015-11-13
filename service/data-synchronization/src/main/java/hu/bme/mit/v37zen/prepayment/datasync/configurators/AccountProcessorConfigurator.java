package hu.bme.mit.v37zen.prepayment.datasync.configurators;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * 
 * @author Dániel Kiss
 *
 */
@ManagedResource(
        objectName="bean:name=SyncMessageAccountProcessorConfigurator",
        description="AccountProcessorConfigurator")
public class AccountProcessorConfigurator extends BaseConfigurator {

	private String accountSelector;
	private String nameSelector;
	private String name2Selector;
	private String statusSelector;
	private String accountTypeSelector;
	private String accountClassSelector;
	private String phonNumberSelector;
	private String mridSelector;
	private String parameterNamespace;

	@ManagedAttribute
	public String getMridSelector() {
		return mridSelector;
	}

	@ManagedAttribute
	public void setMridSelector(String mridSelector) {
		this.mridSelector = mridSelector;
	}

	@ManagedAttribute
	public String getNameSelector() {
		return nameSelector;
	}

	@ManagedAttribute
	public void setNameSelector(String nameSelector) {
		this.nameSelector = nameSelector;
	}

	@ManagedAttribute
	public String getName2Selector() {
		return name2Selector;
	}

	@ManagedAttribute
	public void setName2Selector(String name2Selector) {
		this.name2Selector = name2Selector;
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
	public String getAccountTypeSelector() {
		return accountTypeSelector;
	}

	@ManagedAttribute
	public void setAccountTypeSelector(String accountTypeSelector) {
		this.accountTypeSelector = accountTypeSelector;
	}

	@ManagedAttribute
	public String getAccountClassSelector() {
		return accountClassSelector;
	}

	@ManagedAttribute
	public void setAccountClassSelector(String accountClassSelector) {
		this.accountClassSelector = accountClassSelector;
	}

	@ManagedAttribute
	public String getPhonNumberSelector() {
		return phonNumberSelector;
	}

	@ManagedAttribute
	public void setPhonNumberSelector(String phonNumberSelector) {
		this.phonNumberSelector = phonNumberSelector;
	}

	@ManagedAttribute
	public String getAccountSelector() {
		return accountSelector;
	}

	@ManagedAttribute
	public void setAccountSelector(String accountSelector) {
		this.accountSelector = accountSelector;
	}

	@ManagedAttribute
	public String getParameterNamespace() {
		return parameterNamespace;
	}

	@ManagedAttribute
	public void setParameterNamespace(String parameterNamespace) {
		this.parameterNamespace = parameterNamespace;
	}
	
}
