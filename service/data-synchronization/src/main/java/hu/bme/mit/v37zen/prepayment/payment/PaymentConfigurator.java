package hu.bme.mit.v37zen.prepayment.payment;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "bean:name=PaymentProcessorConfigurator", description = "PaymentProcessorConfigurator")
public class PaymentConfigurator extends BaseConfigurator {

	private String paymentSelector;

	private String accountIdSelector;
	private String statusSelector;
	private String valueSelector;
	private String curencySelector;
	private String dateSelector;

	//private String parameterNamespace;

	@ManagedAttribute
	public String getPaymentSelector() {
		return paymentSelector;
	}

	@ManagedAttribute
	public void setPaymentSelector(String paymentSelector) {
		this.paymentSelector = paymentSelector;
	}

	@ManagedAttribute
	public String getAccountIdSelector() {
		return accountIdSelector;
	}

	@ManagedAttribute
	public void setAccountIdSelector(String accountIdSelector) {
		this.accountIdSelector = accountIdSelector;
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
	public String getValueSelector() {
		return valueSelector;
	}

	@ManagedAttribute
	public void setValueSelector(String valueSelector) {
		this.valueSelector = valueSelector;
	}

	@ManagedAttribute
	public String getCurencySelector() {
		return curencySelector;
	}

	@ManagedAttribute
	public void setCurencySelector(String curencySelector) {
		this.curencySelector = curencySelector;
	}

	@ManagedAttribute
	public String getDateSelector() {
		return dateSelector;
	}

	@ManagedAttribute
	public void setDateSelector(String dateSelector) {
		this.dateSelector = dateSelector;
	}

//	@ManagedAttribute
//	public String getParameterNamespace() {
//		return parameterNamespace;
//	}
//
//	@ManagedAttribute
//	public void setParameterNamespace(String parameterNamespace) {
//		this.parameterNamespace = parameterNamespace;
//	}

}
