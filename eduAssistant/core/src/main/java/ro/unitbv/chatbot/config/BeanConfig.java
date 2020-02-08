package ro.unitbv.chatbot.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import io.fouad.jtb.core.JTelegramBot;
import ro.unitbv.chatbot.handler.ChatbotUpdateHandler;

@Configuration
@ComponentScan({"ro.unitbv"})
@PropertySource("classpath:default.properties")
public class BeanConfig {
	
	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	@Value("${telegram.token}")
	private String TELEGRAM_TOKEN;
	@Value("${telegram.name}")
	private String TELEGRAM_NAME;
	
	@Autowired
	private ChatbotUpdateHandler updateHandler;
	
	@Bean
	public JTelegramBot getTelegramBot() {
		LOGGER.info("Initilizate the chatbot with name: "+TELEGRAM_NAME);
		JTelegramBot bot = new JTelegramBot(TELEGRAM_NAME, TELEGRAM_TOKEN, updateHandler);
		return bot;
	}
	
}
