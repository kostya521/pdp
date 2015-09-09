package com.epam.pdp.spring.logger;

import com.epam.pdp.spring.Event;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache = new LinkedList<Event>();

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        if (cache.size() < cacheSize) {
            cache.add(event);
        } else {
            for (Event e : cache) {
                super.logEvent(e);
            }
            cache.clear();
            cache.add(event);
        }
    }

    @Override
    public void init() throws FileNotFoundException {
        super.init();
    }

    public void destroy() {
        for (Event e : cache) {
            super.logEvent(e);
        }
    }
}
