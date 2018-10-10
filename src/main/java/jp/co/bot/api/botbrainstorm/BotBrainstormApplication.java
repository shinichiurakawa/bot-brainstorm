package jp.co.bot.api.botbrainstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("beans-botapi-service.xml")
@SpringBootApplication
public class BotBrainstormApplication {

	public static void main(String[] args) {
		SpringApplication.run(BotBrainstormApplication.class, args);
	}
}
