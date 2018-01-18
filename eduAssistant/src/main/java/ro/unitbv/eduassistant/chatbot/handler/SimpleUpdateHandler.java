package ro.unitbv.eduassistant.chatbot.handler;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@Service
public class SimpleUpdateHandler implements UpdateHandler {

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
		try {
			LOGGER.info(" The recived message: " + message.getText());
			String response = "Dummy respone from the bot";
			ApiBuilder.api(telegramBotApi).sendMessage(response).toChatId(message.getChat().getId())
					.asReplyToMessage(message.getMessageId()).asSilentMessage().parseMessageAs(ParseMode.MARKDOWN)
					.execute();
		} catch (NegativeResponseException | IOException e) {
			LOGGER.error("Error occured on Update Handler ", e);
		}

	}

}
