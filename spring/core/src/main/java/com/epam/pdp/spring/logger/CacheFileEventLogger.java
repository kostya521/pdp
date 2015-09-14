package com.epam.pdp.spring.logger;

import com.epam.pdp.spring.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

//@Component("cacheEventLogger")
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

    //    @PostConstruct
    @Override
    public void init() throws FileNotFoundException {
        super.init();
    }

    //    @PreDestroy
    public void destroy() {
        for (Event e : cache) {
            super.logEvent(e);
        }
    }
}
