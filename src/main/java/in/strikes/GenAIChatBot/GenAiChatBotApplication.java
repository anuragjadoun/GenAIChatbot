package in.strikes.GenAIChatBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenAiChatBotApplication {

	public static void main(String[] args) {

		System.out.println("OPENROUTER_API_KEY PRESENT: " +
				(System.getenv("OPENROUTER_API_KEY") != null));
		SpringApplication.run(GenAiChatBotApplication.class, args);
	}

}
