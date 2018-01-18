package ro.unitbv.eduassistant.chatbot.handler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fouad.jtb.core.JTelegramBot;
import io.fouad.jtb.core.builders.ApiBuilder;
import io.fouad.jtb.core.builders.ReplyMarkupBuilder;
import io.fouad.jtb.core.enums.ParseMode;
import io.fouad.jtb.core.exceptions.NegativeResponseException;

@Service 
public class SenderService {

	@Autowired
	private JTelegramBot bot;
	
	
	public void sendQuestionToRegisteredStudents(String lessonSessionKey,String questionId) throws IOException, NegativeResponseException {
		
		ApiBuilder.api(bot).sendMessage("Vlad sended a new Question for lessonSessionKey "+lessonSessionKey+" and  questionId "+questionId).toChatId(201164731).parseMessageAs(ParseMode.PLAIN)
          .disableLinkPreviews()
          .asSilentMessage()
          .asReplyToMessage(1)
          .applyReplyMarkup(ReplyMarkupBuilder.forceReply().toReplyMarkup())
          .execute();
	
	}
}
