package com.epam.pdp.spring.logger;

import com.epam.pdp.spring.Event;

import java.util.LinkedList;
import java.util.List;

public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers = new LinkedList<EventLogger>();

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers.addAll(loggers);
    }

    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }

}
