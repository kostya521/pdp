package com.epam.pdp.spring.logger;

import com.epam.pdp.spring.Event;

public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }

}
