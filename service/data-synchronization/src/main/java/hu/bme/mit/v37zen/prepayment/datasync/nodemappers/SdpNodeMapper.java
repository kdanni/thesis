package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.SdpProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.datamodel.Parameter;
import hu.bme.mit.v37zen.sm.jpa.datamodel.ServiceDeliveryPoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.xml.xpath.XPathException;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.springframework.xml.xpath.XPathParseException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class SdpNodeMapper extends AbstractNodeMapper<ServiceDeliveryPoint> {
	
	public static Logger logger = LoggerFactory.getLogger(SdpNodeMapper.class);
	
	private SdpProcessorConfigurator sdpProcessorConfigurator;

	public SdpNodeMapper(SdpProcessorConfigurator sdpProcessorConfigurator, NamespaceHandler namespaces) {
		super(namespaces);
		this.sdpProcessorConfigurator = sdpProcessorConfigurator;
	}

	public ServiceDeliveryPoint mapNode(Node node, int nodeNum)
			throws DOMException {
		
		ServiceDeliveryPoint sdp = new ServiceDeliveryPoint();
		StringBuffer buff = new StringBuffer();
		
		String mRID = evaluate(sdpProcessorConfigurator.getMridSelector(), node);
		buff.append("SDP MRID: "+ mRID + '\n');
		sdp.setMRID(mRID);
		
		String serviceType = evaluate(sdpProcessorConfigurator.getServiceTypeSelector(), node);
		buff.append("SDP ServiceType: "+ serviceType + '\n');
		sdp.setServiceType(serviceType);
		
		String premiseId = evaluate(sdpProcessorConfigurator.getPremiseIdSelector(), node);
		buff.append("SDP PremiseId: "+ premiseId + '\n');
		sdp.setPremiseId(premiseId);
		
		String virtualInd = evaluate(sdpProcessorConfigurator.getVirtualIndSelector(), node);
		buff.append("SDP VirtualInd: "+ virtualInd + '\n');
		sdp.setVirtualInd(virtualInd);
		
		String universalId = evaluate(sdpProcessorConfigurator.getUniversalIdSelector(), node);
		buff.append("SDP UniversalId: "+ universalId + '\n');
		sdp.setUniversalId(universalId);
		
		String billingHoldFlag = evaluate(sdpProcessorConfigurator.getBillingHoldFlagSelector(), node);
		buff.append("SDP BillingHoldFlag: "+ billingHoldFlag + '\n');
		sdp.setBillingHoldFlag(billingHoldFlag);
		
		try {
			XPathExpression expr = XPathExpressionFactory.createXPathExpression(
					".//" + sdpProcessorConfigurator.getParameterNamespace() + ":parameter",
					namespaces.getNamespaces());
			List<Parameter> paramList = expr.evaluate(node, new ParameterNodeMapper(namespaces.getNamespaces(),
					sdpProcessorConfigurator.getParameterNamespace(), sdpProcessorConfigurator.getDateFormat()));
			for (Parameter parameter : paramList) {
				sdp.addParameter(parameter);
			}
			buff.append("SDP Parameters: "+ paramList.toString() + '\n');
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
		} catch (XPathException e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("[New SDP:]\n" + buff.toString());
		
		return sdp;
	}
	
	public SdpProcessorConfigurator getSdpProcessorConfigurator() {
		return sdpProcessorConfigurator;
	}

	public void setSdpProcessorConfigurator(
			SdpProcessorConfigurator sdpProcessorConfigurator) {
		this.sdpProcessorConfigurator = sdpProcessorConfigurator;
	}
}
