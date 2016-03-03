package edu.umich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HwfSurveyFunctionalTestsApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(HwfSurveyFunctionalTestsApplication.class);
		ConfigurableApplicationContext context = app.run(args);
	}
}
