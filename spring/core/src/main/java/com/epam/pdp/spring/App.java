package com.epam.pdp.spring;

import com.epam.pdp.spring.logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event) {
        String msg = event.getMessage().replaceAll(client.getId(), client.getFullName());
        event.setMessage(msg);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = ctx.getBean("app", App.class);

        Event event = ctx.getBean("event", Event.class);
        event.setMessage("some event for 1");
        app.logEvent(event);

        ctx.close();
    }
}
