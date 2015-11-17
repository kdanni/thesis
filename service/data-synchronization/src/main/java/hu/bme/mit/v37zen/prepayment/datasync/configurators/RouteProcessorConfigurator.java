package hu.bme.mit.v37zen.prepayment.datasync.configurators;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(
		objectName = "bean:name=RouteProcessorConfigurator", 
		description = "RouteProcessorConfigurator")
public class RouteProcessorConfigurator extends BaseConfigurator {

	private String routeSelcetor;
	private String mridSelector;
	private String typeSelector;
	private String statusSelector;
	private String billingCycleSelector;
	private String readingCycleSelector;
	private String reconciliationLockFlag;

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
	public String getRouteSelcetor() {
		return routeSelcetor;
	}

	@ManagedAttribute
	public void setRouteSelcetor(String routeSelcetor) {
		this.routeSelcetor = routeSelcetor;
	}

	@ManagedAttribute
	public String getTypeSelector() {
		return typeSelector;
	}

	@ManagedAttribute
	public void setTypeSelector(String typeSelector) {
		this.typeSelector = typeSelector;
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
	public String getBillingCycleSelector() {
		return billingCycleSelector;
	}

	@ManagedAttribute
	public void setBillingCycleSelector(String billingCycleSelector) {
		this.billingCycleSelector = billingCycleSelector;
	}

	@ManagedAttribute
	public String getReadingCycleSelector() {
		return readingCycleSelector;
	}

	@ManagedAttribute
	public void setReadingCycleSelector(String readingCycleSelector) {
		this.readingCycleSelector = readingCycleSelector;
	}

	@ManagedAttribute
	public String getReconciliationLockFlag() {
		return reconciliationLockFlag;
	}

	@ManagedAttribute
	public void setReconciliationLockFlag(String reconciliationLockFlag) {
		this.reconciliationLockFlag = reconciliationLockFlag;
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
