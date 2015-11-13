package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.ServiceLocationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.xml.AbstractNodeMapper;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.jpa.datamodel.Parameter;
import hu.bme.mit.v37zen.sm.jpa.datamodel.ServiceLocation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.xml.xpath.XPathException;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.springframework.xml.xpath.XPathParseException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ServiceLocationNodeMapper extends AbstractNodeMapper<ServiceLocation> {
	
	public static Logger logger = LoggerFactory.getLogger(ServiceLocationNodeMapper.class);
	
	private ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator;
	
	public ServiceLocationNodeMapper(ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator,
			NamespaceHandler namespaces) {
		super(namespaces);
		this.serviceLocationProcessorConfigurator = serviceLocationProcessorConfigurator;
	}


	public ServiceLocation mapNode(Node node, int nodeNum) throws DOMException {
		
		ServiceLocation sl = new ServiceLocation();
		StringBuffer buff = new StringBuffer();
		
		String mRID = evaluate(serviceLocationProcessorConfigurator.getMridSelector(), node);
		buff.append("ServiceLocation MRID: "+ mRID + '\n');
		sl.setMRID(mRID);
		
		String addressGeneral = evaluate(serviceLocationProcessorConfigurator.getAddressGeneralSelector(), node);
		buff.append("ServiceLocation AddressGeneral: "+ addressGeneral + '\n');
		sl.setAddressGeneral(addressGeneral);
		
		String addressLine2 = evaluate(serviceLocationProcessorConfigurator.getAddressLine2Selector(), node);
		buff.append("ServiceLocation AddressLine2: "+ addressLine2 + '\n');
		sl.setAddressLine2(addressLine2);
		
		String city = evaluate(serviceLocationProcessorConfigurator.getCitySelector(), node);
		buff.append("ServiceLocation City: "+ city + '\n');
		sl.setCity(city);
		
		String country = evaluate(serviceLocationProcessorConfigurator.getCountrySelector(), node);
		buff.append("ServiceLocation Country: "+ country + '\n');
		sl.setCountry(country);
		
		String latitude = evaluate(serviceLocationProcessorConfigurator.getLatitudeSelector(), node);
		buff.append("ServiceLocation Latitude: "+ latitude + '\n');
		sl.setLatitude(latitude);
		
		String locationCode = evaluate(serviceLocationProcessorConfigurator.getLocationCodeSelector(), node);
		buff.append("ServiceLocation LocationCode: "+ locationCode + '\n');
		sl.setLocationCode(locationCode);
		
		String locationType = evaluate(serviceLocationProcessorConfigurator.getLocationTypeSelector(), node);
		buff.append("ServiceLocation LocationType: "+ locationType + '\n');
		sl.setLocationType(locationType);
		
		String longitude = evaluate(serviceLocationProcessorConfigurator.getLongitudeSelector(), node);
		buff.append("ServiceLocation Longitude: "+ longitude + '\n');
		sl.setLongitude(longitude);
		
		String poBox = evaluate(serviceLocationProcessorConfigurator.getPoBoxSelector(), node);
		buff.append("ServiceLocation PoBox: "+ poBox + '\n');
		sl.setPoBox(poBox);
		
		String postalCode = evaluate(serviceLocationProcessorConfigurator.getPostalCodeSelector(), node);
		buff.append("ServiceLocation PostalCode: "+ postalCode + '\n');
		sl.setPostalCode(postalCode);
		
		String stateOrProvince = evaluate(serviceLocationProcessorConfigurator.getStateOrProvinceSelector(), node);
		buff.append("ServiceLocation StateOrProvince: "+ stateOrProvince + '\n');
		sl.setStateOrProvince(stateOrProvince);
		
		String timeZone = evaluate(serviceLocationProcessorConfigurator.getTimeZoneSelector(), node);
		buff.append("ServiceLocation TimeZone: "+ timeZone + '\n');
		sl.setTimeZone(timeZone);
				

		try {
			XPathExpression expr = XPathExpressionFactory.createXPathExpression(
					".//" + serviceLocationProcessorConfigurator.getParameterNamespace() + ":parameter",
					namespaces.getNamespaces());
			List<Parameter> paramList = expr.evaluate(node, new ParameterNodeMapper(namespaces.getNamespaces(),
					serviceLocationProcessorConfigurator.getParameterNamespace(), serviceLocationProcessorConfigurator.getDateFormat()));
			for (Parameter parameter : paramList) {
				sl.addParameter(parameter);
			}
			buff.append("ServiceLocation Parameters: "+ paramList.toString() + '\n');
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
		} catch (XPathException e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("[New ServiceLocation:]\n" + buff.toString());
		
		return sl;
		
	}
	public ServiceLocationProcessorConfigurator getServiceLocationProcessorConfigurator() {
		return serviceLocationProcessorConfigurator;
	}

	public void setServiceLocationProcessorConfigurator(
			ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator) {
		this.serviceLocationProcessorConfigurator = serviceLocationProcessorConfigurator;
	}	
}
