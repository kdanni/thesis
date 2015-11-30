package hu.bme.mit.v37zen.prepayment.dataprocessing.persister;

import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.prepayment.PrepaymentAccount;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.Account;
import hu.bme.mit.v37zen.sm.datamodel.util.merge.AccountMerger;
import hu.bme.mit.v37zen.sm.jpa.repositories.AccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentAccountRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

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
import org.springframework.transaction.annotation.Transactional;

public class AccountPersister implements MessageHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(AccountPersister.class);
	
	private SubscribableChannel channel;
		
	private MessageChannel outputChannel;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PrepaymentAccountRepository prepaymentAccountRepository;

	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	@Override
	@Transactional
	public void handleMessage(Message<?> message) throws MessagingException {
		
		Object payload = message.getPayload();
		if(payload instanceof Account){
			Account account = (Account)payload;
			List<Account> accList = accountRepository.findByMRID(account.getMRID());
			if(accList.size() == 0){
				account = this.accountRepository.save(account);
			} else if (accList.size() == 1){
				account = AccountMerger.merge(accList.get(0), account);
				account = this.accountRepository.save(account);
			} else {
				String msg = "Multiple Account found with the same id!";
				logger.error(msg);
				PrepaymentException pe = new PrepaymentException(new Date(), msg);
				prepaymentExceptionRepository.save(pe);
				return;
			}
			
			List<PrepaymentAccount> ppaccList = prepaymentAccountRepository.findByAccountMRID(account.getMRID());
			if(ppaccList.isEmpty()){
				
				PrepaymentAccount ppacc = new PrepaymentAccount();
				ppacc.setAccountMRID(account.getMRID());
				prepaymentAccountRepository.save(ppacc);
			}			
			
			//outputChannel.send(new GenericMessage<Account>(account));
		}
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
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

	public PrepaymentAccountRepository getPrepaymentAccountRepository() {
		return prepaymentAccountRepository;
	}

	public void setPrepaymentAccountRepository(
			PrepaymentAccountRepository prepaymentAccountRepository) {
		this.prepaymentAccountRepository = prepaymentAccountRepository;
	}

	public PrepaymentExceptionRepository getPrepaymentExceptionRepository() {
		return prepaymentExceptionRepository;
	}

	public void setPrepaymentExceptionRepository(
			PrepaymentExceptionRepository prepaymentExceptionRepository) {
		this.prepaymentExceptionRepository = prepaymentExceptionRepository;
	}

}
