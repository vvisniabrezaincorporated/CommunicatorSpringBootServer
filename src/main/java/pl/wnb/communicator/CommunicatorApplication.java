package pl.wnb.communicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CommunicatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunicatorApplication.class, args);
	}

}

