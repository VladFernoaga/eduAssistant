package ro.unitbv.charbot.starter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.fouad.jtb.core.JTelegramBot;

@Configuration
public class ChatbotStarter {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	
	@Autowired
	private JTelegramBot bot;
	
	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			LOGGER.info("Start the registered chatbot");
			bot.start();
		};
	}
	
}
