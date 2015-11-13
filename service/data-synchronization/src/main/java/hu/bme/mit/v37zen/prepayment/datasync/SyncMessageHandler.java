/**
 * 
 */
package hu.bme.mit.v37zen.prepayment.datasync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.w3c.dom.Document;

/**
 * @author Dániel Kiss
 *
 */
public class SyncMessageHandler implements ApplicationContextAware {
	
	public final static Logger logger = LoggerFactory.getLogger(SyncMessageHandler.class);
	
	private ThreadPoolTaskExecutor taskExecutor;
	
	private ApplicationContext applicationContext;
	
	public SyncMessageHandler(ThreadPoolTaskExecutor taskExecutorPool) {
		
		this.taskExecutor = taskExecutorPool;
	}
	
	public void process(Document document) {
		logger.info("Sync message has arrived."); 
		//String xml = (new DOMNodeToString()).nodeToString(document);
		//logger.debug("[Sync message:]" + '\n' + xml);
		
		SyncMessageProcessor messageProccesor = applicationContext.getBean(SyncMessageProcessor.class);
		messageProccesor.setXmlNode(document);
		taskExecutor.execute(messageProccesor);
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
