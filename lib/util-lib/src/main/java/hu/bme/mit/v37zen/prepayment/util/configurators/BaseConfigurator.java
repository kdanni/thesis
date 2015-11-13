package hu.bme.mit.v37zen.prepayment.util.configurators;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.jmx.export.annotation.ManagedAttribute;

public class BaseConfigurator {
	public static final String DEFAULT_PATTERN = "YYYY-MM-dd'T'HH:mm:ss";
	
	private String dateFormatPattern = DEFAULT_PATTERN;
	private DateFormat dateFormat = new SimpleDateFormat(DEFAULT_PATTERN);
	
	@ManagedAttribute
	public String getDateFormatPattern() {
		return dateFormatPattern;
	}

	@ManagedAttribute
	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
		this.dateFormat = new SimpleDateFormat(dateFormatPattern);
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}
}
