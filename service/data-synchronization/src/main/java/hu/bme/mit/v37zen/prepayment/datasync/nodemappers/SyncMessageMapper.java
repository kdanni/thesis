package hu.bme.mit.v37zen.prepayment.datasync.nodemappers;

import hu.bme.mit.v37zen.prepayment.datasync.configurators.AccountProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.AssociationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.ContactProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.MeterProcessorConfirugarator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.RouteProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.SdpProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.datasync.configurators.ServiceLocationProcessorConfigurator;
import hu.bme.mit.v37zen.prepayment.util.xml.NamespaceHandler;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountContactAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Contact;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Route;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpMeterAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpRouteAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.SdpServiceLocationAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceLocation;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.xml.xpath.XPathException;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.springframework.xml.xpath.XPathParseException;
import org.w3c.dom.Node;

public class SyncMessageMapper {
	public static Logger logger = LoggerFactory.getLogger(SyncMessageMapper.class);
	
	private NamespaceHandler namespaces;
	
	private AccountProcessorConfigurator accountProcessorConfigurator;
	private SdpProcessorConfigurator sdpProcessorConfigurator;
	private AssociationProcessorConfigurator associationProcessorConfigurator;
	private MeterProcessorConfirugarator meterProcessorConfirugarator;
	private ContactProcessorConfigurator contactProcessorConfigurator;
	private ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator;
	private RouteProcessorConfigurator routeProcessorConfigurator;
	
	public SyncMessageMapper(NamespaceHandler namespaces, 
			AccountProcessorConfigurator accountProcessorConfigurator,
			SdpProcessorConfigurator sdpProcessorConfigurator,
			AssociationProcessorConfigurator associationProcessorConfigurator,
			MeterProcessorConfirugarator meterProcessorConfirugarator,
			ContactProcessorConfigurator contactProcessorConfigurator,
			ServiceLocationProcessorConfigurator serviceLocationProcessorConfigurator,
			RouteProcessorConfigurator routeProcessorConfigurator) 
	{
		super();
		this.namespaces = namespaces;
		this.accountProcessorConfigurator = accountProcessorConfigurator;
		this.sdpProcessorConfigurator = sdpProcessorConfigurator;
		this.associationProcessorConfigurator = associationProcessorConfigurator;
		this.meterProcessorConfirugarator = meterProcessorConfirugarator;
		this.contactProcessorConfigurator = contactProcessorConfigurator;
		this.serviceLocationProcessorConfigurator = serviceLocationProcessorConfigurator;
		this.routeProcessorConfigurator = routeProcessorConfigurator; 
		
	}
	
	public static class SyncData {
		
		private List<Account> Accounts;
		private List<ServiceDeliveryPoint> ServiceDeliveryPoints;
		private List<MeterAsset> MeterAssets;
		private List<Contact> Contacts;
		private List<ServiceLocation> ServiceLocations;
		private List<Route> Routes;
		private List<SdpMeterAssociation> SdpMeterAssociations;
		private List<AccountSDPAssociation> AccountSDPAssociations;
		private List<SdpServiceLocationAssociation> SdpServiceLocationAssociations;
		private List<AccountContactAssociation> AccountContactAssociations;
		private List<SdpRouteAssociation> SdpRouteAssociations;
		
		public List<Account> getAccounts() {
			return Accounts;
		}
		public void setAccounts(List<Account> accounts) {
			Accounts = accounts;
		}
		public List<ServiceDeliveryPoint> getServiceDeliveryPoints() {
			return ServiceDeliveryPoints;
		}
		public void setServiceDeliveryPoints(
				List<ServiceDeliveryPoint> serviceDeliveryPoints) {
			ServiceDeliveryPoints = serviceDeliveryPoints;
		}
		public List<MeterAsset> getMeterAssets() {
			return MeterAssets;
		}
		public void setMeterAssets(List<MeterAsset> meterAssets) {
			MeterAssets = meterAssets;
		}
		public List<Contact> getContacts() {
			return Contacts;
		}
		public void setContacts(List<Contact> contacts) {
			Contacts = contacts;
		}
		public List<ServiceLocation> getServiceLocations() {
			return ServiceLocations;
		}
		public void setServiceLocations(List<ServiceLocation> serviceLocations) {
			ServiceLocations = serviceLocations;
		}
		public List<SdpMeterAssociation> getSdpMeterAssociations() {
			return SdpMeterAssociations;
		}
		public void setSdpMeterAssociations(
				List<SdpMeterAssociation> sdpMeterAssociations) {
			SdpMeterAssociations = sdpMeterAssociations;
		}
		public List<AccountSDPAssociation> getAccountSDPAssociations() {
			return AccountSDPAssociations;
		}
		public void setAccountSDPAssociations(
				List<AccountSDPAssociation> accountSDPAssociations) {
			AccountSDPAssociations = accountSDPAssociations;
		}
		public List<SdpServiceLocationAssociation> getSdpServiceLocationAssociations() {
			return SdpServiceLocationAssociations;
		}
		public void setSdpServiceLocationAssociations(
				List<SdpServiceLocationAssociation> sdpServiceLocationAssociations) {
			SdpServiceLocationAssociations = sdpServiceLocationAssociations;
		}
		public List<AccountContactAssociation> getAccountContactAssociations() {
			return AccountContactAssociations;
		}
		public void setAccountContactAssociations(
				List<AccountContactAssociation> accountContactAssociations) {
			AccountContactAssociations = accountContactAssociations;
		}
		public SyncData(
				List<Account> accounts,
				List<ServiceDeliveryPoint> serviceDeliveryPoints,
				List<MeterAsset> meterAssets,
				List<Contact> contacts,
				List<ServiceLocation> serviceLocations,
				List<Route> routes,
				List<SdpMeterAssociation> sdpMeterAssociations,
				List<AccountSDPAssociation> accountSDPAssociations,
				List<SdpServiceLocationAssociation> sdpServiceLocationAssociations,
				List<AccountContactAssociation> accountContactAssociations,
				List<SdpRouteAssociation> sdpRouteAssociations) {
			super();
			Accounts = accounts;
			ServiceDeliveryPoints = serviceDeliveryPoints;
			MeterAssets = meterAssets;
			Contacts = contacts;
			ServiceLocations = serviceLocations;
			SdpMeterAssociations = sdpMeterAssociations;
			AccountSDPAssociations = accountSDPAssociations;
			SdpServiceLocationAssociations = sdpServiceLocationAssociations;
			AccountContactAssociations = accountContactAssociations;
		}
		public List<Route> getRoutes() {
			return Routes;
		}
		public void setRoutes(List<Route> routes) {
			Routes = routes;
		}
		public List<SdpRouteAssociation> getSdpRouteAssociations() {
			return SdpRouteAssociations;
		}
		public void setSdpRouteAssociations(List<SdpRouteAssociation> sdpRouteAssociations) {
			SdpRouteAssociations = sdpRouteAssociations;
		}		
	}
	
	public SyncData mapSyncMessage(Node syncMessage){
		
		List<Account> accList = this.processAccounts(syncMessage);
		List<ServiceDeliveryPoint> sdpList = this.processSDP(syncMessage);
		List<MeterAsset> maList = this.processMeterAssets(syncMessage);
		List<Contact> cList = this.processContacts(syncMessage);
		List<ServiceLocation> slList = this.processServiceLocations(syncMessage);
		List<Route> rList = this.processRoutes(syncMessage);
		List<SdpMeterAssociation> smrList = this.processSDPMeterAssetAssociations(syncMessage);
		List<AccountSDPAssociation> asaList = this.processAccountSDPAssociations(syncMessage);
		List<SdpServiceLocationAssociation> sdpslrList = this.processSDPServiceLocationAssociations(syncMessage);
		List<AccountContactAssociation> acaList = this.processAccountContactAssociations(syncMessage);
		List<SdpRouteAssociation> sraList = this.processSdpRouteAssociations(syncMessage);
		
		return new SyncData(accList, sdpList, maList, cList, slList, rList, smrList, asaList, sdpslrList, acaList , sraList);	
	}
	
	protected XPathExpression createXPathExpression(String expression, Map<String,String> namespaces){
		if(expression == null || expression.isEmpty()){
			return null;
		}		
		try{	
			return XPathExpressionFactory.createXPathExpression(expression, namespaces);
		} catch (XPathParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	protected List<Account> processAccounts(Node node){
		XPathExpression expr = createXPathExpression(this.accountProcessorConfigurator.getAccountSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<Account> accountList;		
		try {
			accountList = expr.evaluate(node, new AccountNodeMapper(accountProcessorConfigurator, namespaces));
			return accountList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
		
	}
	
	protected List<ServiceDeliveryPoint> processSDP(Node node) {
		XPathExpression expr = createXPathExpression(this.sdpProcessorConfigurator.getSdpSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<ServiceDeliveryPoint> sdpList;
		try {
			sdpList = expr.evaluate(node, new SdpNodeMapper(sdpProcessorConfigurator, namespaces));
			return sdpList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	protected List<Route> processRoutes(Node node) {
		XPathExpression expr = createXPathExpression(this.routeProcessorConfigurator.getRouteSelcetor(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<Route> routeList;
		try {
			routeList = expr.evaluate(node, new RouteNodeMapper(routeProcessorConfigurator, namespaces));
			return routeList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	protected List<SdpRouteAssociation> processSdpRouteAssociations(Node node) {
		XPathExpression expr = createXPathExpression(this.associationProcessorConfigurator.getSdpRouteAssociationSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<SdpRouteAssociation> sdpRouteAssList;
		try {
			sdpRouteAssList = expr.evaluate(node, new SdpRouteAssociationNodeMapper(associationProcessorConfigurator, namespaces));
			return sdpRouteAssList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	protected List<AccountSDPAssociation> processAccountSDPAssociations(Node node){
		XPathExpression expr = createXPathExpression(this.associationProcessorConfigurator.getAccountSdpAssociationSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<AccountSDPAssociation> accSdpAssList;
		try {
			accSdpAssList = expr.evaluate(node, new AccountSdpAssociationNodeMapper(associationProcessorConfigurator, namespaces));
			return accSdpAssList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	protected List<MeterAsset> processMeterAssets(Node node){
		XPathExpression expr = createXPathExpression(this.meterProcessorConfirugarator.getMeterAssetSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<MeterAsset> meterList;
		try {
			meterList = expr.evaluate(node, new MeterAssetNodeMapper(this.meterProcessorConfirugarator, namespaces));
			return meterList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	protected List<SdpMeterAssociation> processSDPMeterAssetAssociations(Node node){
		XPathExpression expr = createXPathExpression(this.associationProcessorConfigurator.getSdpMeterAssociationSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<SdpMeterAssociation> sdpMeterAssList;
		try {
			sdpMeterAssList = expr.evaluate(node, new SdpMeterAssociationNodeMapper(this.associationProcessorConfigurator, namespaces));
			return sdpMeterAssList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	protected List<Contact> processContacts(Node node){
		XPathExpression expr = createXPathExpression(this.contactProcessorConfigurator.getContactSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<Contact> contactList;
		try {
			contactList = expr.evaluate(node, new ContactNodeMapper(contactProcessorConfigurator, namespaces));
			return contactList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	protected List<ServiceLocation>  processServiceLocations(Node node){
		XPathExpression expr = createXPathExpression(this.serviceLocationProcessorConfigurator.getServiceLocationSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<ServiceLocation> slList;
		try {
			slList = expr.evaluate(node, new ServiceLocationNodeMapper(serviceLocationProcessorConfigurator, namespaces));
			return slList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	protected List<SdpServiceLocationAssociation> processSDPServiceLocationAssociations(Node node){
		XPathExpression expr = createXPathExpression(this.associationProcessorConfigurator.getSdpServiceLocationAssociationSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<SdpServiceLocationAssociation> sdpSlList;
		try {
			sdpSlList = expr.evaluate(node, 
					new SdpServiceLocationAssociationNodeMapper(associationProcessorConfigurator, namespaces));
			return sdpSlList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	protected List<AccountContactAssociation> processAccountContactAssociations(Node node){
		XPathExpression expr = createXPathExpression(this.associationProcessorConfigurator.getAccountContactAssociationSelector(), namespaces.getNamespaces());
		if (expr == null){
			return null;
		}
		List<AccountContactAssociation> accContAssList;
		try {
			accContAssList = expr.evaluate(node, 
					new AccountContactAssociationNodeMapper(associationProcessorConfigurator, namespaces));
			return accContAssList;
		} catch (XPathException e) {
			logger.error(e.getMessage());
			return null;
		}
	}



	public RouteProcessorConfigurator getRouteProcessorConfigurator() {
		return routeProcessorConfigurator;
	}



	public void setRouteProcessorConfigurator(RouteProcessorConfigurator routeProcessorConfigurator) {
		this.routeProcessorConfigurator = routeProcessorConfigurator;
	}
	
}
