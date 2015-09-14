package com.epam.pdp.spring;

import com.epam.pdp.spring.logger.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;

@Configuration
public class LoggersConfig {

    @Bean(name = "consoleEventLogger")
    EventLogger consoleEventLogger() {
        return new ConsoleEventLogger();
    }

    @Bean(name = "fileEventLogger", initMethod = "init")
    FileEventLogger fileEventLogger(@Value("${fileName}") String fileName) {
        return new FileEventLogger(fileName);
    }

    @Bean(name = "cacheFileEventLogger", initMethod = "init", destroyMethod = "destroy")
    EventLogger cacheFileEventLogger(@Value("${fileName}") String fileName,
                                     @Value("${cacheSize}") int cacheSize) {
        return new CacheFileEventLogger(fileName, cacheSize);
    }

    @Bean(name = "combinedEventLogger")
    EventLogger combinedEventLogger(@Qualifier("consoleEventLogger") final EventLogger consoleEventLogger,
                                    @Qualifier("fileEventLogger") final EventLogger fileEventLogger) {
        return new CombinedEventLogger(new LinkedList<EventLogger>() {{
            add(consoleEventLogger);
            add(fileEventLogger);
        }});
    }

}
