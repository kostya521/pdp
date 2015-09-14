package com.epam.pdp.spring;

import com.epam.pdp.spring.events.EventType;
import com.epam.pdp.spring.logger.EventLogger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

@Configuration
@Import(LoggersConfig.class)
@ComponentScan(basePackages = "com.epam.pdp.spring")
public class AppConfig {

    @Bean(name = "app")
    App app(Client client,
            @Qualifier("consoleEventLogger") EventLogger defaultConsoleLogger,
            @Qualifier("consoleEventLogger") EventLogger consoleEventLogger,
            @Qualifier("fileEventLogger") EventLogger fileEventLogger) {
        HashMap<String, EventLogger> loggers = new HashMap<String, EventLogger>();
        loggers.put(EventType.INFO.toString(), consoleEventLogger);
        loggers.put(EventType.ERROR.toString(), fileEventLogger);

        return new App(client, defaultConsoleLogger, loggers);
    }

    @Bean(name = "event")
    @Scope("prototype")
    Event event() {
        return new Event(new Date(), DateFormat.getDateInstance());
    }

    @Bean
    PropertyPlaceholderConfigurer configurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ClassPathResource[] resources = new ClassPathResource[]
                {new ClassPathResource("app.properties")};
        ppc.setLocations(resources);
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    @Bean
    @Scope("prototype")
    Client client(@Value("${id}") String clientId,
                  @Value("${name}") String clientName,
                  @Value("${greeting}") String greeting) {
        return new Client(clientId, clientName, greeting);
    }

}
