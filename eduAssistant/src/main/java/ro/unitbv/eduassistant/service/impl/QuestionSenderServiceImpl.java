package ro.unitbv.eduassistant.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fouad.jtb.core.JTelegramBot;
import io.fouad.jtb.core.builders.ApiBuilder;
import io.fouad.jtb.core.enums.ParseMode;
import io.fouad.jtb.core.exceptions.NegativeResponseException;
import ro.unitbv.eduassistant.model.LessonSession;
import ro.unitbv.eduassistant.repo.LessonSessionRepo;
import ro.unitbv.eduassistant.service.QuestionSenderService;

@Service 
public class QuestionSenderServiceImpl implements QuestionSenderService{

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	private JTelegramBot bot;
	
	@Autowired 
	private LessonSessionRepo lessonSessionRepo;
	
	@Override
	public void sendQuestionToRegisteredStudents(String lessonSessionKey,String questionId) {
		
		
		LessonSession lessonSession = lessonSessionRepo.findBySessionKey(lessonSessionKey).orElseThrow(
				() -> new IllegalArgumentException(String.format("The sessionKey %s is invalid", lessonSessionKey)));

		List<Long> registeredStudents = lessonSession.getRegistations().stream().map(reg -> Long.parseLong(reg.getStudent().getName())).collect(Collectors.toList());
		
		registeredStudents.forEach(chatId -> sendQuestion("TODO add a real question", chatId));
		
		LOGGER.info("Finish sending the question to all students");
	
	}
	
	private void sendQuestion(String question, long chatId){
		
		try {
			ApiBuilder.api(bot).sendMessage(question).toChatId(chatId).parseMessageAs(ParseMode.HTML).execute();
			LOGGER.debug(String.format("Question was send to chatid %s",chatId));
		} catch (NegativeResponseException | IOException e) {
			LOGGER.error("Error occured when tryning to reply to a message ", e);
			throw new IllegalStateException("Error occured when tryning to reply to a message",e);
		}
	
	}
}
