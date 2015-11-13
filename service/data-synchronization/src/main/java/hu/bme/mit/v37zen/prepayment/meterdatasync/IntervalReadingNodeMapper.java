package hu.bme.mit.v37zen.prepayment.meterdatasync;

import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.prepayment.util.xml.XPathUtil;
import hu.bme.mit.v37zen.sm.jpa.datamodel.meterreading.IntervalReading;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

public class IntervalReadingNodeMapper {
		
	public static Logger logger = LoggerFactory.getLogger(IntervalReadingNodeMapper.class);
	
	private NamespaceHandler namespaces;
	
	private IntervalReadingConfigurator intervalReadingConfigurator;
	
	
	public IntervalReadingNodeMapper(IntervalReadingConfigurator intervalReadingConfigurator, NamespaceHandler namespaces) {
		this.namespaces = namespaces;
		this.intervalReadingConfigurator = intervalReadingConfigurator;
	}
	
	public List<IntervalReading> mapMeterReading(Node meterReading){
		List<IntervalReading> intervalReadings = new ArrayList<IntervalReading>();
		StringBuffer sb = new StringBuffer();
		
		String meterId = evaluateAsString(
				intervalReadingConfigurator.getMeterIdSelector(), meterReading);
		String meterIdType = evaluateAsString(
				intervalReadingConfigurator.getMeterIdTypeSelector(), meterReading);
		String meterIdNamespace = evaluateAsString(
				intervalReadingConfigurator.getMeterIdNamespaceSelector(), meterReading);
		
		List<Node> intervalBlocks = evaluateAsNodeList(
				intervalReadingConfigurator.getIntervalBlockSelector(), meterReading);
		
		for (Node intervalBlock : intervalBlocks) {
			UUID readingBlock = UUID.randomUUID();
			String readingTypeId = evaluateAsString(
					intervalReadingConfigurator.getReadingTypeIdSelector(), intervalBlock);
			Integer intervalLength = evaluateAsInteger(
					intervalReadingConfigurator.getIntervalLengthSelector(), intervalBlock); 
		
			
			List<Node> readings = evaluateAsNodeList(
					intervalReadingConfigurator.getiReadingSelector(), intervalBlock);
			
			for (Node reading : readings) {
				
				Double value = evaluateAsDouble(
						intervalReadingConfigurator.getValueSelector(), reading);
				
				Date endTime = evaluateAsDate(
						intervalReadingConfigurator.getEndTimeSelector(), reading);
				
				IntervalReading ir = new IntervalReading();
				
				ir.setReferenceId(meterId);
				ir.setReferenceIdType(meterIdType);
				ir.setReferenceIdNamepsace(meterIdNamespace);
				
				ir.setReadingBlock(readingBlock.toString());
				
				if(intervalLength != null){
					ir.setIntervalLength(intervalLength);
				}
				ir.setReadingTypeId(readingTypeId);
				
				ir.setEndTime(endTime);
				ir.setValue(value);
				
				intervalReadings.add(ir);
				sb.append(ir.toString() + '\n');
			}
		}
		logger.debug("Iterval Readings:\n" + sb.toString());
		return intervalReadings;
	}
	
	public List<IntervalReading> mapMeterDataMessage(Node meterDataMessage){
		
		List<IntervalReading> intervalReadings = new ArrayList<IntervalReading>();
		
		List<Node> meterReadings = evaluateAsNodeList(
				intervalReadingConfigurator.getMeterReadingSelector(), meterDataMessage);
			
		for (Node node : meterReadings) {			
			intervalReadings.addAll(mapMeterReading(node)); 
		}
		
		return intervalReadings;
	}
		
	protected List<Node> evaluateAsNodeList(String expression, Node node){
		return XPathUtil.evaluateAsNodeList(expression, node, namespaces.getNamespaces());
	}
	
	protected String evaluateAsString(String expression, Node node){
		return XPathUtil.evaluateAsString(expression, node, namespaces.getNamespaces());
	}
	
	protected Integer evaluateAsInteger(String expression, Node node){
		return XPathUtil.evaluateAsInteger(expression, node, namespaces.getNamespaces());
	}
	
	protected Double evaluateAsDouble(String expression, Node node){
		return XPathUtil.evaluateAsDouble(expression, node, namespaces.getNamespaces());
	}	
	protected Boolean evaluateAsBoolean(String expression, Node node){
		return XPathUtil.evaluateAsBoolean(expression, node, namespaces.getNamespaces());
	}
	protected Date evaluateAsDate(String expression, Node node){
		return XPathUtil.evaluateAsDate(expression, intervalReadingConfigurator.getDateFormat(),
				node, namespaces.getNamespaces());
	}
}
