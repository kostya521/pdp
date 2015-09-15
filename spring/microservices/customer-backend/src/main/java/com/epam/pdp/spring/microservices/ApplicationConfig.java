package com.epam.pdp.spring.microservices;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;

@Configuration
public class ApplicationConfig {
    @Named
    static class JerseyConfig extends ResourceConfig {
        public JerseyConfig() {
            this.packages("com.epam.pdp.spring.microservices.rest");
        }
    }
}
