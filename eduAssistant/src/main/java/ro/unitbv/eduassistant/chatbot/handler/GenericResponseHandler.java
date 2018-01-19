package ro.unitbv.eduassistant.chatbot.handler;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fouad.jtb.core.TelegramBotApi;
import io.fouad.jtb.core.UpdateHandler;
import io.fouad.jtb.core.beans.CallbackQuery;
import io.fouad.jtb.core.beans.ChosenInlineResult;
import io.fouad.jtb.core.beans.InlineQuery;
import io.fouad.jtb.core.beans.Message;
import io.fouad.jtb.core.builders.ApiBuilder;
import io.fouad.jtb.core.enums.ParseMode;
import io.fouad.jtb.core.exceptions.NegativeResponseException;
import ro.unitbv.eduassistant.service.RegistrationService;

@Service
public class GenericResponseHandler implements UpdateHandler {

	@Autowired
	private RegistrationService registrationService;
	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	public void onCallbackQueryReceived(TelegramBotApi arg0, int arg1, CallbackQuery arg2) {
	}

	public void onChosenInlineResultReceived(TelegramBotApi arg0, int arg1, ChosenInlineResult arg2) {
	}

	public void onEditedMessageReceived(TelegramBotApi arg0, int arg1, Message arg2) {
	}

	public void onGetUpdatesFailure(Exception e) {
		LOGGER.error("Error occured on Update Handler ", e);
	}

	public void onInlineQueryReceived(TelegramBotApi arg0, int arg1, InlineQuery arg2) {
	}

	public void onMessageReceived(TelegramBotApi telegramBotApi, int id, Message message) {
		String messageText = message.getText();
		String textWords[] = messageText.split("\\s+");
		LOGGER.info(String.format("The recived message from chatId: %s contains following words: %s", message.getChat().getId(), Arrays.toString(textWords)));
		
		String command = textWords[0].toUpperCase();
		
		switch (command) {
		case "REG":
			if(textWords.length <=1){
				sendResponse(telegramBotApi,message.getChat().getId(), message.getMessageId(), "*Please provide a session key for registration*");
				break;
			}
			String sessionKey = textWords[1]; 
			String response = registrationService.registerNewStudentInSession(sessionKey, message.getChat().getId()+"");
			sendResponse(telegramBotApi, message.getChat().getId(), message.getMessageId(), response);
			break;
		default:
			sendResponse(telegramBotApi, message.getChat().getId(), message.getMessageId(), "*Please provide a valid command. Call help to see what's avaialable*");
			break;
		}
	
//		
//		try {
//			LOGGER.info(" The recived message: " + message.getText()+" chatId: "+message.getChat().getId());
//			String response = "Dummy respone from the bot";
//			ApiBuilder.api(telegramBotApi).sendMessage(response).toChatId(message.getChat().getId())
//					.asReplyToMessage(message.getMessageId()).asSilentMessage().parseMessageAs(ParseMode.MARKDOWN)
//					.execute();
//		} catch (NegativeResponseException | IOException e) {
//			LOGGER.error("Error occured on Update Handler ", e);
//		}

	}
	
	
	private void sendResponse(TelegramBotApi telegramBotApi, long chatId, int messageId, String response){
		try {
			ApiBuilder.api(telegramBotApi).sendMessage(response).toChatId(chatId)
			.asReplyToMessage(messageId).asSilentMessage().parseMessageAs(ParseMode.MARKDOWN)
			.execute();
		} catch (NegativeResponseException | IOException e) {
			LOGGER.error("Error occured when tryning to reply to a message ", e);
			throw new IllegalStateException("Error occured when tryning to reply to a message",e);
		}

	}

}
