package com.opendev3.devjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevJournalApplication.class, args);
	}

}
