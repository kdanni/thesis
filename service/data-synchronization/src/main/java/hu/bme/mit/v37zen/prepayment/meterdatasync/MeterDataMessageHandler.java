/**
 * 
 */
package hu.bme.mit.v37zen.prepayment.meterdatasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.w3c.dom.Document;

/**
 * @author dani
 *
 */
public class MeterDataMessageHandler implements ApplicationContextAware {

	public final static Logger logger = LoggerFactory.getLogger(MeterDataMessageHandler.class);
	
	private ThreadPoolTaskExecutor taskExecutor;
	
	private ApplicationContext applicationContext;
		
	public MeterDataMessageHandler(ThreadPoolTaskExecutor taskExecutorPool) {
		
		this.taskExecutor = taskExecutorPool;
	}
	
	public void process(Document document) {		
		logger.info("Meter Data message has arrived.");
		//String xml = (new DOMNodeToString()).nodeToString(document);
		//logger.debug('\n' + xml);
		
		MeterDataProcessor ratingProcessor = applicationContext.getBean(MeterDataProcessor.class);
		ratingProcessor.setXmlNode(document);
		taskExecutor.execute(ratingProcessor);
	}
	
	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		
		this.applicationContext = applicationContext;
	}
}
