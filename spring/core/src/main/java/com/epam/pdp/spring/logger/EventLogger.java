package com.epam.pdp.spring.logger;


import com.epam.pdp.spring.Event;

public interface EventLogger {
    void logEvent(Event event);
}
