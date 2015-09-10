package com.epam.pdp.spring;

import com.epam.pdp.spring.events.EventType;
import com.epam.pdp.spring.logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Map<EventType, EventLogger> loggers;
    private Client client;
    private EventLogger defaultLogger;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = ctx.getBean("app", App.class);

        Event event = ctx.getBean("event", Event.class);
        event.setMessage("some event for 1");

        app.logEvent(EventType.INFO, event);
        app.logEvent(EventType.ERROR, event);

        ctx.close();
    }
}
