package ro.unitbv.eduassistant.config.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@PropertySource("classpath:application-dev.properties")
public class BeanConfigDev {

}
