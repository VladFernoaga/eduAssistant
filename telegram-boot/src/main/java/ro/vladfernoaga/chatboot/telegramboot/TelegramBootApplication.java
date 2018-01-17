package ro.vladfernoaga.chatboot.telegramboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.fouad.jtb.core.JTelegramBot;
import ro.vladfernoaga.chatboot.messageBuilder.dao.SearchApisDao;


@SpringBootApplication
@ComponentScan({"ro.vladfernoaga.chatboot.messageBuilder","ro.vladfernoaga.chatboot.telegramboot"})
public class TelegramBootApplication {

	/** The Constant LOGGER. */
	public static final Logger LOGGER = LogManager.getLogger();

    private static final String API_TOKEN = "363877872:AAEGG9_770o2V7y_yVZfy2_zZjpweUp4J8Y";
    private static final String BOOT_NAME = "easyShopper_bot";


	public static void main(String[] args) {
		SpringApplication.run(TelegramBootApplication.class, args);
	}

	@Autowired
	private SearchApisDao searchApis;
	
	@Autowired
	private SimpleUpdateHandler updateHandler;
	
	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			//Test DataBase conncetion
			LOGGER.info("Search Api thru Dao: "+searchApis.findAll());
			
			LOGGER.info("My chat boot is started!");
			JTelegramBot bot = new JTelegramBot(BOOT_NAME, API_TOKEN,updateHandler);

			LOGGER.info("Chatboot inistialized - start() will ocure");
			bot.start();

		};
	}
}
