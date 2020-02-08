package ro.unitbv.chatbot.config.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@PropertySource("classpath:application-test.properties")
public class BeanConfigProd {

}
