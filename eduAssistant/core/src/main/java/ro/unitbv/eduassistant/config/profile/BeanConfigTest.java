package ro.unitbv.eduassistant.config.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
@PropertySource("classpath:application-test.properties")
public class BeanConfigTest {

}
