package hu.bme.mit.v37zen.prepayment.util.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtil {
	
	public static Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
	
	public static Date stringToDate(String datetime, DateFormat format){		
		Date date = null;
		if(datetime != null && !datetime.isEmpty()){
			try {
				date = format.parse(datetime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				date = new DateTime(datetime).toDate();
			}		
		}
		return date;
	}
}
