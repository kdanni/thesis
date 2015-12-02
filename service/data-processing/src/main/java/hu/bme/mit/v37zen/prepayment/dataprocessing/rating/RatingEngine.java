package hu.bme.mit.v37zen.prepayment.dataprocessing.rating;

import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.ValidationException;
import hu.bme.mit.v37zen.prepayment.dataprocessing.validation.seeddata.AccountValidator;
import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.meterreading.IntervalReading;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Balance;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.Payment;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.jpa.repositories.BalanceRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.IntervalReadingRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PaymentRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentAccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class RatingEngine implements MessageHandler {

	public static final Logger logger = LoggerFactory.getLogger(RatingEngine.class);
	
	private static ConcurrentSkipListSet<String> toProcessAccountId = new ConcurrentSkipListSet<String>();
	
	@Value("${validation.association.active.status}")
	private String activeStatus;
	
	@Value("${validation.association.inactive.status}")
	private String inactiveStatus;

	private SubscribableChannel channel;
	
	private MessageChannel outputChannel;
	
	@Autowired
	private AccountValidator accountValidator;
	
	@Autowired
	private IntervalReadingRepository intervalReadingRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private BalanceRepository balanceRepository;
	
	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;

	@Autowired
	private PrepaymentAccountRepository prepaymentAccountRepository;
	
	@Override
	public void handleMessage(Message<?> message) throws MessagingException {
		Object payload = message.getPayload();
		if(payload instanceof String){
			String mRID = (String) payload;
			try {
				if(accountValidator.isAccountExist(mRID)){
					toProcessAccountId.add(mRID);
					logger.debug("Account scheduled for rating: " + mRID);
				}
			} catch (ValidationException e) {
				logException(e.getMessage());
			}
			catch (Exception e) {
				logger.error(e.getMessage(),e);
			}		
		}
		
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED,transactionManager="transactionManager")
	public void rate(){
		try{
			//logger.debug("rating..");
			while(!toProcessAccountId.isEmpty()){
				try {
				
					this.transaction();
					
				} catch (ValidationException e) {
					this.logException(e.getMessage());
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}		
	}
	
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.REQUIRED,
			rollbackFor={ValidationException.class},transactionManager="transactionManager")
	protected synchronized void transaction() throws ValidationException {
		
		String mRID = toProcessAccountId.pollFirst();

		List<PrepaymentAccount> ppaccList = this.prepaymentAccountRepository.findByAccountMRID(mRID);
		if(ppaccList.size() != 1){
			String msg = ((ppaccList.size() == 0) ? "No" : "Multiple" ) 
					+ " PrepaymentAccount found with id: " + mRID +".";
			throw new ValidationException(msg);
		}
		PrepaymentAccount ppacc = ppaccList.get(0);
				
		List<IntervalReading> intervalReadings = intervalReadingRepository.getIntervalReadingForProccessing(ppacc.getMeterMRID());
		double consumption = 0;
		for (IntervalReading intervalReading : intervalReadings) {
			consumption += intervalReading.getValue();
			intervalReading.setProcessed(true);
		}
		
		consumption = new BasicRate().rate(consumption);
		
		double prepaid = 0;
		List<Payment> payments = paymentRepository.getPaymentForProccessing(mRID);
		for (Payment payment : payments) {
			prepaid += payment.getValue();
			payment.setProcessed(true);
		}
		
		List<Balance> result = balanceRepository.findByAccountIdOrderByDateDesc(mRID, new PageRequest(0, 1));
		if(result.size() < 2){
			double balance = 0;
			if(result.size() == 1){
				Balance b = result.get(0);
				if(b != null){
					balance = b.getBalance();
				}
			}										
			Balance newBalance = new Balance(balance + prepaid - consumption, prepaid, consumption, mRID, new Date());
			
			ppacc = prepaymentAccountRepository.findByIdFetchBalance(ppacc.getId());
			ppacc.getBalance().add(newBalance);
			this.prepaymentAccountRepository.save(ppacc);
			
			ppacc = prepaymentAccountRepository.findByIdFetchMeterReading(ppacc.getId());
			ppacc.getMeterReadings().addAll(intervalReadings);
			this.prepaymentAccountRepository.save(ppacc);
		
			ppacc = prepaymentAccountRepository.findByIdFetchPayment(ppacc.getId());
			ppacc.getPayments().addAll(payments);
			this.prepaymentAccountRepository.save(ppacc);
			
			this.outputChannel.send(new GenericMessage<String>(mRID));					
			logger.debug("New Balance: " + newBalance.toString());	
		}
	}
	
	@Transactional
	protected void logException(String msg){
		logger.error("[Exception]: " + msg);
		PrepaymentException pe = new PrepaymentException(new Date(), msg);
		prepaymentExceptionRepository.save(pe);
	}

	public SubscribableChannel getChannel() {
		return channel;
	}

	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		this.channel.subscribe(this);
	}

	public MessageChannel getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(MessageChannel outputChannel) {
		this.outputChannel = outputChannel;
	}

	public AccountValidator getAccountValidator() {
		return accountValidator;
	}

	public void setAccountValidator(AccountValidator accountValidator) {
		this.accountValidator = accountValidator;
	}

	public PrepaymentAccountRepository getPrepaymentAccountRepository() {
		return prepaymentAccountRepository;
	}

	public void setPrepaymentAccountRepository(
			PrepaymentAccountRepository prepaymentAccountRepository) {
		this.prepaymentAccountRepository = prepaymentAccountRepository;
	}

	public IntervalReadingRepository getIntervalReadingRepository() {
		return intervalReadingRepository;
	}

	public void setIntervalReadingRepository(IntervalReadingRepository intervalReadingRepository) {
		this.intervalReadingRepository = intervalReadingRepository;
	}

	public PaymentRepository getPaymentRepository() {
		return paymentRepository;
	}

	public void setPaymentRepository(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public BalanceRepository getBalanceRepository() {
		return balanceRepository;
	}

	public void setBalanceRepository(BalanceRepository balanceRepository) {
		this.balanceRepository = balanceRepository;
	}
}
