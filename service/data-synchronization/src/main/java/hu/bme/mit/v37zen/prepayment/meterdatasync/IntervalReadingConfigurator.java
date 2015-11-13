package hu.bme.mit.v37zen.prepayment.meterdatasync;

import hu.bme.mit.v37zen.prepayment.util.configurators.BaseConfigurator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * 
 * @author Dániel Kiss
 *
 */
@ManagedResource(
        objectName="bean:name=IntervalReadingConfigurator",
        description="IntervalReadingConfigurator")
public class IntervalReadingConfigurator extends BaseConfigurator {

	private String meterReadingSelector;
	private String meterIdSelector;
	private String meterIdTypeSelector;
	private String meterIdNamespaceSelector;
	private String intervalBlockSelector;
	private String readingTypeIdSelector;
	private String intervalLengthSelector;
	private String iReadingSelector;
	private String valueSelector;
	private String endTimeSelector;
	
	@ManagedAttribute
	public String getMeterReadingSelector() {
		return meterReadingSelector;
	}

	@ManagedAttribute
	public void setMeterReadingSelector(String meterReadingSelector) {
		this.meterReadingSelector = meterReadingSelector;
	}

	@ManagedAttribute
	public String getMeterIdSelector() {
		return meterIdSelector;
	}

	@ManagedAttribute
	public void setMeterIdSelector(String meterIdSelector) {
		this.meterIdSelector = meterIdSelector;
	}

	@ManagedAttribute
	public String getMeterIdTypeSelector() {
		return meterIdTypeSelector;
	}

	@ManagedAttribute
	public void setMeterIdTypeSelector(String meterIdTypeSelector) {
		this.meterIdTypeSelector = meterIdTypeSelector;
	}

	@ManagedAttribute
	public String getMeterIdNamespaceSelector() {
		return meterIdNamespaceSelector;
	}

	@ManagedAttribute
	public void setMeterIdNamespaceSelector(String meterIdNamespaceSelector) {
		this.meterIdNamespaceSelector = meterIdNamespaceSelector;
	}

	@ManagedAttribute
	public String getIntervalBlockSelector() {
		return intervalBlockSelector;
	}

	@ManagedAttribute
	public void setIntervalBlockSelector(String intervalBlockSelector) {
		this.intervalBlockSelector = intervalBlockSelector;
	}

	@ManagedAttribute
	public String getReadingTypeIdSelector() {
		return readingTypeIdSelector;
	}

	@ManagedAttribute
	public void setReadingTypeIdSelector(String readingTypeIdSelector) {
		this.readingTypeIdSelector = readingTypeIdSelector;
	}

	@ManagedAttribute
	public String getIntervalLengthSelector() {
		return intervalLengthSelector;
	}

	@ManagedAttribute
	public void setIntervalLengthSelector(String intervalLengthSelector) {
		this.intervalLengthSelector = intervalLengthSelector;
	}

	@ManagedAttribute
	public String getiReadingSelector() {
		return iReadingSelector;
	}

	@ManagedAttribute
	public void setiReadingSelector(String iReadingSelector) {
		this.iReadingSelector = iReadingSelector;
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
	public String getEndTimeSelector() {
		return endTimeSelector;
	}

	@ManagedAttribute
	public void setEndTimeSelector(String endTimeSelector) {
		this.endTimeSelector = endTimeSelector;
	}
}
