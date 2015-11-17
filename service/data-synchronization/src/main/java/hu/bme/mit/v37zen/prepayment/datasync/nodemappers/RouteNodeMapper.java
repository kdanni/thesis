package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import java.util.List;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.RouteProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Parameter;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.xml.xpath.XPathException;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.springframework.xml.xpath.XPathParseException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class RouteNodeMapper extends AbstractNodeMapper<Route> {

	public static Logger logger = LoggerFactory.getLogger(RouteNodeMapper.class);
	
	private RouteProcessorConfigurator routeProcessorConfigurator;
	
	public RouteNodeMapper(RouteProcessorConfigurator routeProcessorConfigurator, 
			NamespaceHandler namespaces) {
		super(namespaces);
		this.routeProcessorConfigurator = routeProcessorConfigurator;
	}

	@Override
	public Route mapNode(Node node, int nodeNum) throws DOMException {
		
		Route route = new Route();
		StringBuffer buff = new StringBuffer();
		
		String mRID = evaluate(routeProcessorConfigurator.getMridSelector(), node);
		buff.append("Route MRID: "+ mRID + '\n');
		route.setMRID(mRID);
		
		String billingCycle = evaluate(routeProcessorConfigurator.getBillingCycleSelector(), node);
		buff.append("Route BillingCycle: "+ billingCycle + '\n');
		route.setBillingCycle(billingCycle);
		
		String readingCycle = evaluate(routeProcessorConfigurator.getReadingCycleSelector(), node);
		buff.append("Route ReadingCycle: "+ readingCycle + '\n');
		route.setReadingCycle(readingCycle);
		
		String type = evaluate(routeProcessorConfigurator.getTypeSelector(), node);
		buff.append("Route Type: "+ type + '\n');
		route.setType(type);
		
		String status = evaluate(routeProcessorConfigurator.getStatusSelector(), node);
		buff.append("Route Status: "+ status + '\n');
		route.setStatus(status);

		String reconciliationLockFlag = evaluate(routeProcessorConfigurator.getReconciliationLockFlag(), node);
		buff.append("Route ReconciliationLockFlag: "+ reconciliationLockFlag + '\n');
		route.setReconciliationLockFlag(reconciliationLockFlag);

		try {
			XPathExpression expr = XPathExpressionFactory.createXPathExpression(
					".//" + routeProcessorConfigurator.getParameterNamespace() + ":parameter",
					namespaces.getNamespaces());
			List<Parameter> paramList = expr.evaluate(node, new ParameterNodeMapper(namespaces.getNamespaces(),
					routeProcessorConfigurator.getParameterNamespace(), routeProcessorConfigurator.getDateFormat()));
			for (Parameter parameter : paramList) {
				route.addParameter(parameter);
			}
			buff.append("Route Parameters: "+ paramList.toString() + '\n');
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
		} catch (XPathException e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("[New Route:]\n" + buff.toString());
		
		return route;
	}

	public RouteProcessorConfigurator getRouteProcessorConfigurator() {
		return routeProcessorConfigurator;
	}

	public void setRouteProcessorConfigurator(RouteProcessorConfigurator routeProcessorConfigurator) {
		this.routeProcessorConfigurator = routeProcessorConfigurator;
	}

}
