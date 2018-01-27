package ro.unitbv.eduassistant.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.fouad.jtb.core.JTelegramBot;
import ro.unitbv.eduassistant.chatbot.handler.EduAssistantUpdateHandler;

@Configuration
@ComponentScan({"ro.unitbv.eduassistant"})
public class BeanConfig {
	
	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	private static final String API_TOKEN = "482992037:AAFx4yC92gN5u8HOZfYYKOCjn6RBjlONngI";
	private static final String BOOT_NAME = "edu_assistant_bot";

	@Autowired
	private EduAssistantUpdateHandler updateHandler;
	
	@Bean
	public JTelegramBot getTelegramBot() {
		LOGGER.info("Initilizate the chatbot with name: "+BOOT_NAME);
		JTelegramBot bot = new JTelegramBot(BOOT_NAME, API_TOKEN, updateHandler);
		return bot;
	}
}
