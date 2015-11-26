package hu.bme.mit.v37zen.prepayment.meterdatasync;

import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.jpa.repositories.IntervalReadingRepository;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.w3c.dom.Node;

public class MeterDataProcessorImpl implements MeterDataProcessor {

	public static Logger logger = LoggerFactory.getLogger(MeterDataProcessorImpl.class);
	
	private Node xmlNode;
	
	private NamespaceHandler namespaces;
	
	private IntervalReadingConfigurator intervalReadingConfigurator;
	
	private ApplicationContext applicationContext;

	
	public MeterDataProcessorImpl(NamespaceHandler namespaceHandler){
		
		this.namespaces = namespaceHandler;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void run() {
		if(this.xmlNode == null){
			logger.warn("Meter reading message is null!"); 
			return;
		}
		logger.info("Meter reading processing has started."); 
		try {
	
			
			List<IntervalReading> intervalReadings = new ArrayList<IntervalReading>();
			intervalReadings = new IntervalReadingNodeMapper(intervalReadingConfigurator, namespaces).mapMeterDataMessage(xmlNode);
			
			IntervalReadingRepository irr = this.applicationContext.getBean(IntervalReadingRepository.class);
			irr.save(intervalReadings);
			
			JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
			for (final IntervalReading intervalReading : intervalReadings) {
				jmsTemplate.send(new MessageCreator() {
					@Override
					public Message createMessage(Session session)
							throws JMSException {
						return session
								.createObjectMessage(intervalReading);
					}
				});
			}
			logger.info("Meter reading processing has finished."); 
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.info("Meter reading processing has failed."); 
		}		
	}

	public Node getXmlNode() {
		return xmlNode;
	}

	public void setXmlNode(Node xmlNode) {
		this.xmlNode = xmlNode;
	}

	public NamespaceHandler getNamespaces() {
		return namespaces;
	}

	public void setNamespaces(NamespaceHandler namespaces) {
		this.namespaces = namespaces;
	}

	public IntervalReadingConfigurator getIntervalReadingConfigurator() {
		return intervalReadingConfigurator;
	}

	public void setIntervalReadingConfigurator(
			IntervalReadingConfigurator intervalReadingConfigurator) {
		this.intervalReadingConfigurator = intervalReadingConfigurator;
	}
	
}
