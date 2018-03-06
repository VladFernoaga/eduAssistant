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
import com.pengrad.telegrambot.response.SendResponse;

@Service
public class SimpleUpdateHandler implements UpdatesListener {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private TelegramBot bot;

	
	@Override
	public int process(List<Update> updates) {
		
		SendMessage request = new SendMessage(updates.get(0).message().from().id(), "hello World")
		        .parseMode(ParseMode.HTML)
		        .disableWebPagePreview(true)
		        .disableNotification(true)
		        .replyToMessageId(1)
		        .replyMarkup(new ForceReply());

		// sync
		bot.execute(request);
		
		return 0;
	}

}
