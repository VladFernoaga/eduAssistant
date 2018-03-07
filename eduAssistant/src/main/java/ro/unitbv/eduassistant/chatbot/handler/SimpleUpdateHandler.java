package ro.unitbv.eduassistant.chatbot.handler;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

@Service
public class SimpleUpdateHandler implements UpdatesListener {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private TelegramBot bot;

	
	@Override
	public int process(List<Update> updates) {
		
		for (Update update : updates) {
			Integer chatId = update.message().from().id();
			String messageText = update.message().text();
			Integer messageId = update.message().messageId();
			
			SendMessage request = new SendMessage(chatId, String.format("<b>Hello World</b> I recived your message: %s",messageText))
					.parseMode(ParseMode.HTML)
					.disableNotification(false)
					.replyToMessageId(messageId)
					.replyMarkup(new ForceReply())
					;
			bot.execute(request);
		}
		
		return UpdatesListener.CONFIRMED_UPDATES_ALL;
	}

}
