package ro.vladfernoaga.chatboot.telegramboot.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.vladfernoaga.chatboot.messageBuilder.ai.service.AttributExtractorService;
import ro.vladfernoaga.chatboot.messageBuilder.service.impl.UrlBuilderServiceImpl;
import ro.vladfernoaga.chatboot.telegramboot.service.MessageInterpreteService;
@Service
public class MessageInterpreterServiceImpl  implements MessageInterpreteService{

	private static final long SHOP_ID =189l;
	private static final String REPLAY_STANDARD_MESG = "We found for you following: ";
	
	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private UrlBuilderServiceImpl urlBuilder;
	@Autowired
	private AttributExtractorService attrExtractorService;
	
	@Override
	public String interpretMessage(String message) {
		
		LOGGER.info("Start interpreting the message: "+message);
		List<String> attributes = new ArrayList<>(attrExtractorService.getAttributesFromText(message));
		return REPLAY_STANDARD_MESG + urlBuilder.getUrlsForArttributes(attributes, SHOP_ID);
	}

}
