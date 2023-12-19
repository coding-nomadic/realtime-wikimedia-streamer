package com.wikimedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WikimediaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WikimediaConsumerApplication.class, args);
	}
}
