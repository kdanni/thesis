package hu.bme.mit.v37zen.prepayment.dataprocessing.persister;

import hu.bme.mit.v37zen.sm.datamodel.audit.PrepaymentException;
import hu.bme.mit.v37zen.sm.datamodel.smartmetering.MeterAsset;
import hu.bme.mit.v37zen.sm.datamodel.util.merge.MeterAssetMerger;
import hu.bme.mit.v37zen.sm.jpa.repositories.MeterAssetRepository;
import hu.bme.mit.v37zen.sm.jpa.repositories.PrepaymentExceptionRepository;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.transaction.annotation.Transactional;

public class MeterPersister implements MessageHandler {
	
	public static final Logger logger = LoggerFactory.getLogger(MeterPersister.class);
	
	private SubscribableChannel channel;
	
	@Autowired
	private MeterAssetRepository meterAssetRepository;

	@Autowired
	private PrepaymentExceptionRepository prepaymentExceptionRepository;
	
	@Override
	@Transactional
	public void handleMessage(Message<?> message) throws MessagingException {
		
		Object payload = message.getPayload();
		if(payload instanceof MeterAsset){
			MeterAsset meter = (MeterAsset) payload;
			List<MeterAsset> meterList = meterAssetRepository.findByMRID(meter.getMRID()); 
			
			if(meterList.size() == 0){
				this.meterAssetRepository.save(meter);
			} else if (meterList.size() == 1){
				meter = MeterAssetMerger.merge(meterList.get(0), meter);
				meter = this.meterAssetRepository.save(meter);
			} else {
				String msg = "Multiple MeterAsset found with the same id!";
				logger.error(msg);
				PrepaymentException pe = new PrepaymentException(new Date(), msg);
				getPrepaymentExceptionRepository().save(pe);
				return;
			}
		}
	}

	public SubscribableChannel getChannel() {
		return channel;
	}
	
	public void setChannel(SubscribableChannel channel) {
		this.channel = channel;
		channel.subscribe(this);
	}

	public MeterAssetRepository getMeterAssetRepository() {
		return meterAssetRepository;
	}

	public void setMeterAssetRepository(MeterAssetRepository meterAssetRepository) {
		this.meterAssetRepository = meterAssetRepository;
	}

	public PrepaymentExceptionRepository getPrepaymentExceptionRepository() {
		return prepaymentExceptionRepository;
	}

	public void setPrepaymentExceptionRepository(
			PrepaymentExceptionRepository prepaymentExceptionRepository) {
		this.prepaymentExceptionRepository = prepaymentExceptionRepository;
	}
}
