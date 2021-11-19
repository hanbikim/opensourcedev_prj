package com.opendev3.Devjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevjournalApplication {


	public static void main(String[] args) {
		SpringApplication.run(DevjournalApplication.class, args);
	}

}


