package com.wikimedia;

import com.wikimedia.producer.WikimediaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.wikimedia"})
public class WikimediaKafkaProducerApplication implements CommandLineRunner {

    @Autowired
    private WikimediaProducer wikimediaProducer;

    public static void main(String[] args) {
        SpringApplication.run(WikimediaKafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        wikimediaProducer.sendMessage();
    }

    @Bean
    public String beanString() {
        return new String();
    }
}
