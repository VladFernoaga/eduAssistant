package ro.unitbv.standalone;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ro.unitbv.eduassistant.config"})
public class EduAssistantApplication {

  public static void main(String[] args) {
    SpringApplication.run(EduAssistantApplication.class, args);
  }

}

