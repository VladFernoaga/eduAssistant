package ro.unitbv.eduassistant.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pengrad.telegrambot.TelegramBot;

import ro.unitbv.eduassistant.chatbot.handler.SimpleUpdateHandler;

@Configuration
@ComponentScan({"ro.unitbv.eduassistant"})
public class BeanConfig {
	
	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

	private static final String API_TOKEN = "363877872:AAEGG9_770o2V7y_yVZfy2_zZjpweUp4J8Y";
	private static final String BOOT_NAME = "easyShopper_bot";

	@Autowired
	private SimpleUpdateHandler updateHandler;
	
	@Bean
	public CommandLineRunner run() {
		return (args) -> {

			getTelegramBot().setUpdatesListener(updateHandler);
		};
	}
	
	@Bean
	public TelegramBot getTelegramBot() {
		return  new TelegramBot(API_TOKEN);
	}
}
