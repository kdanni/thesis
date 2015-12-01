package hu.bme.mit.v37zen.prepayment.dataprocessing.derivation;

import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.AccountSDPAssociation;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.ServiceDeliveryPoint;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountSDPAssociationRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentAccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.ServiceDeliveryPointRepository;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Transactional;

public class PrepaymentAccountDerivator implements MessageHandler {

	public static final Logger logger = LoggerFactory.getLogger(PrepaymentAccountDerivator.class);
	
	private final static String PROCESSED_KEY = "proccessed";
	
	private final static int REPROCESSING_THRESHOLD = 120;
	
	private SubscribableChannel channel;
	
	private MessageChannel outputChannel;
	
	private MessageChannel rederivatorChannel;
	
	private String inactiveStatus;
	
	private String activeStatus;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountSDPAssociationRepository accountSDPAssociationRepository;

	@Autowired
	private PrepaymentAccountRepository prepaymentAccountRepository;

	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	@Autowired
	private ServiceDeliveryPointRepository serviceDeliveryPointRepository;
	
	@Override
	@Transactional
	public void handleMessage(Message<?> message) throws MessagingException {
		int processed = 0;
		if(message.getHeaders().containsKey(PROCESSED_KEY)){
			processed = message.getHeaders().get(PROCESSED_KEY, Integer.class);
		}
		if(processed > REPROCESSING_THRESHOLD){
			logger.error("Rederivating threshold reached!");
			PrepaymentException pe = new PrepaymentException(new Date(), "Rederivating threshold reached!");
			prepaymentExceptionRepository.save(pe);
			return;
		}
		
		Object payload = message.getPayload();
		if(payload instanceof AccountSDPAssociation){
			AccountSDPAssociation accSdpAss = (AccountSDPAssociation) payload;
			String accMRID = accSdpAss.getAccountMRID();
			if(accMRID == null || accMRID.trim().isEmpty()){
				accMRID = accSdpAss.getAccount() != null ? accSdpAss.getAccount().getMRID() : null;
				accSdpAss.setAccountMRID(accMRID);
			}
						
			List<PrepaymentAccount> ppaccList = prepaymentAccountRepository.findByAccountMRID(accMRID);
			if(ppaccList.size() == 0){
				Message<AccountSDPAssociation> m = new GenericMessage<AccountSDPAssociation>(accSdpAss);
				m.getHeaders().put(PROCESSED_KEY, new Integer(processed+1));
				logger.debug("Rederivating: " + m.toString());
				this.rederivatorChannel.send(m);
				return;
			}
			if(ppaccList.size() > 1){
				String msg = "Multiple PrepaymentAccount found with the same id!";
				logger.error(msg);
				PrepaymentException pe = new PrepaymentException(new Date(), msg);
				prepaymentExceptionRepository.save(pe);
				return;
			}
			List<Account> accList = accountRepository.findByMRID(accMRID);
			if(accList.size() == 0){
				Message<AccountSDPAssociation> m = new GenericMessage<AccountSDPAssociation>(accSdpAss);
				m.getHeaders().put(PROCESSED_KEY, new Integer(processed+1));
				logger.debug("Rederivating: " + m.toString());
				this.rederivatorChannel.send(m);
				return;
			} else if (accList.size() > 1){
				String msg = "Multiple Account found with the same id!";
				logger.error(msg);
				PrepaymentException pe = new PrepaymentException(new Date(), msg);
				prepaymentExceptionRepository.save(pe);
				return;
			}
			
			String sdpMRID = accSdpAss.getSdpMRID();
			if(sdpMRID == null || sdpMRID.trim().isEmpty()){
				sdpMRID = accSdpAss.getServiceDeliveryPoint() != null ? accSdpAss.getServiceDeliveryPoint().getMRID() : null;
				accSdpAss.setSdpMRID(sdpMRID);
			}
			List<ServiceDeliveryPoint> sdpList = serviceDeliveryPointRepository.findByMRID(sdpMRID); 
			if(sdpList.size() == 0){
				Message<AccountSDPAssociation> m = new GenericMessage<AccountSDPAssociation>(accSdpAss);
				m.getHeaders().put(PROCESSED_KEY, new Integer(processed+1));
				logger.debug("Rederivating: " + m.toString());
				this.rederivatorChannel.send(m);
				return;
			} else if (sdpList.size() > 1){
				String msg = "Multiple SDP found with the same id!";
				logger.error(msg);
				PrepaymentException pe = new PrepaymentException(new Date(), msg);
				prepaymentExceptionRepository.save(pe);
				return;
			}

			//Lezárni az aktiv kapcsolatot a megeggyző kapcsolatra.
			List<AccountSDPAssociation> list = accountSDPAssociationRepository.findByAccountMRID(accMRID);
			for (AccountSDPAssociation accountSDPAssociation : list) {
				if(accountSDPAssociation.getSdpMRID() != null && accountSDPAssociation.getSdpMRID().equals(sdpMRID)){
					accountSDPAssociation.setEndDate(new Date());
					accountSDPAssociation.setStatus(inactiveStatus);
				}
			}			
			
			PrepaymentAccount ppacc = ppaccList.get(0);
			Account acc = accList.get(0);
			ServiceDeliveryPoint sdp = sdpList.get(0);
			
			accSdpAss.setAccount(acc);
			accSdpAss.setServiceDeliveryPoint(sdp);
			
			accSdpAss = accountSDPAssociationRepository.save(accSdpAss);
			
			ppacc.setMRID(accMRID);
			ppacc.setStatus(accSdpAss.getStatus());
			ppacc.setActive(true);
			ppacc.setStartDate(new Date());
			ppacc.setAccountSDPAssociation(accSdpAss);
			ppacc = prepaymentAccountRepository.saveAndFlush(ppacc);
			
			logger.debug("PrepaymentAccount derived: " + ppacc.toString());
		}		
	}

	public SubscribableChannel getChannel() {
		return channel;
	}

	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		channel.subscribe(this);
	}

	public MessageChannel getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(MessageChannel outputChannel) {
		this.outputChannel = outputChannel;
	}

	public MessageChannel getRederivatorChannel() {
		return rederivatorChannel;
	}

	public void setRederivatorChannel(MessageChannel rederivatorChannel) {
		this.rederivatorChannel = rederivatorChannel;
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public AccountSDPAssociationRepository getAccountSDPAssociationRepository() {
		return accountSDPAssociationRepository;
	}

	public void setAccountSDPAssociationRepository(
			AccountSDPAssociationRepository accountSDPAssociationRepository) {
		this.accountSDPAssociationRepository = accountSDPAssociationRepository;
	}

	public PrepaymentExceptionRepository getPrepaymentExceptionRepository() {
		return prepaymentExceptionRepository;
	}

	public void setPrepaymentExceptionRepository(
			PrepaymentExceptionRepository prepaymentExceptionRepository) {
		this.prepaymentExceptionRepository = prepaymentExceptionRepository;
	}

	public ServiceDeliveryPointRepository getServiceDeliveryPointRepository() {
		return serviceDeliveryPointRepository;
	}

	public void setServiceDeliveryPointRepository(
			ServiceDeliveryPointRepository serviceDeliveryPointRepository) {
		this.serviceDeliveryPointRepository = serviceDeliveryPointRepository;
	}

	public String getInactiveStatus() {
		return inactiveStatus;
	}

	public void setInactiveStatus(String inactiveStatus) {
		this.inactiveStatus = inactiveStatus;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

}
