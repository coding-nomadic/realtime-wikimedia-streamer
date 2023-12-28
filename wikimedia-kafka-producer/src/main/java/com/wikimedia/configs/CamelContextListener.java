package com.wikimedia.configs;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CamelContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CamelContext camelContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        camelContext.start();
    }
}
