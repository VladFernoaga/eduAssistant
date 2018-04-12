package ro.unitbv.eduassistant.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.fouad.jtb.core.JTelegramBot;
import ro.unitbv.eduassistant.chatbot.handler.EduAssistantUpdateHandler;

@Configuration
@ComponentScan({"ro.unitbv.eduassistant, ro.unitbv.eduassistant.security.config"})
public class BeanConfig {
	
	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	private static final String API_TOKEN_PROD = "482992037:AAFx4yC92gN5u8HOZfYYKOCjn6RBjlONngI";
	private static final String API_TOKEN_TEST = "563717824:AAEMSrIRVLk9_NWDB8fGXmui8C9kys56fiA";
	private static final String BOOT_NAME_PROD = "edu_assistant_bot";
	private static final String BOOT_NAME_TEST = "edu_assistant_bot_test";

	@Autowired
	private EduAssistantUpdateHandler updateHandler;
	
	@Bean
	public JTelegramBot getTelegramBot() {
		LOGGER.info("Initilizate the chatbot with name: "+BOOT_NAME_PROD);
		JTelegramBot bot = new JTelegramBot(BOOT_NAME_PROD, API_TOKEN_PROD, updateHandler);
		return bot;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
