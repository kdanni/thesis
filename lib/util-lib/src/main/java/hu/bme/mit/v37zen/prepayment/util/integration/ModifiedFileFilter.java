package hu.bme.mit.v37zen.prepayment.util.integration;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.file.filters.AbstractFileListFilter;

public class ModifiedFileFilter extends AbstractFileListFilter<File>{
	
	Logger logger = LoggerFactory.getLogger(ModifiedFileFilter.class);

	private final Map<String,Long> seen;

	private final Object monitor = new Object();
	
	private int rejectOlderInDays = Integer.MAX_VALUE;
	

	public ModifiedFileFilter(int initialCapatity) {
		this.seen = new ConcurrentHashMap<String,Long>(initialCapatity);
	}

	public ModifiedFileFilter() {
		this.seen = new ConcurrentHashMap<String,Long>();
	}

	@Override
	protected boolean accept(File file) {
		synchronized (this.monitor) {
			if(rejectOlderInDays != Integer.MAX_VALUE){
				int days = Days.daysBetween(new DateTime(file.lastModified()), new DateTime(new Date())).getDays(); 
				if(days > this.rejectOlderInDays){
					return false;
				}
			}
			try {
				if(seen.containsKey(file.getCanonicalPath())){
					if(file.lastModified() > seen.get(file.getCanonicalPath())){
						seen.put(file.getCanonicalPath(), file.lastModified());
						return true;
					}
				} else {
					seen.put(file.getCanonicalPath(),file.lastModified());
					return true;
				}
			} catch (IOException e) {
				logger.error("IOException" , e);
			}
		}
		return false;
	}

	public long getRejectOlderInDays() {
		return rejectOlderInDays;
	}

	public void setRejectOlderInDays(int rejectOlderInDays) {
		this.rejectOlderInDays =  rejectOlderInDays;
	}

}
