package com.wikimedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"com.wikimedia","org.apache.camel"})
public class WikimediaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WikimediaConsumerApplication.class, args);
	}
}
