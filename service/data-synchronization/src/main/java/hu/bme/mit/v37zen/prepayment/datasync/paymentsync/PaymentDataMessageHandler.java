package hu.bme.mit.v37zen.prepayment.datasync.paymentsync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.w3c.dom.Document;

public class PaymentDataMessageHandler implements ApplicationContextAware {

	public final static Logger logger = LoggerFactory.getLogger(PaymentDataMessageHandler.class);
	
	private ThreadPoolTaskExecutor taskExecutor;
	
	private ApplicationContext applicationContext;
	
	public PaymentDataMessageHandler(ThreadPoolTaskExecutor taskExecutorPool) {
		this.taskExecutor = taskExecutorPool;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public void process(Document document) {		
		logger.info("Payment Data message has arrived.");
		//String xml = (new DOMNodeToString()).nodeToString(document);
		//logger.debug('\n' + xml);
		
		PaymentDataProcessor paymentProcessor = applicationContext.getBean(PaymentDataProcessor.class);
		paymentProcessor.setXmlNode(document);
		taskExecutor.execute(paymentProcessor);
	}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

}
