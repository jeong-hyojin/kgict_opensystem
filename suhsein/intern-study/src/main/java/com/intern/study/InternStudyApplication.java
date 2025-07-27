package com.intern.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InternStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternStudyApplication.class, args);
	}

}
