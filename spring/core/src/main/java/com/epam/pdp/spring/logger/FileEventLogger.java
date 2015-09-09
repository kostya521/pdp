package com.epam.pdp.spring.logger;

import com.epam.pdp.spring.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file = null;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws FileNotFoundException {
        this.file = new File(fileName);
        if (!this.file.canWrite()) {
            throw new FileNotFoundException(fileName);
        }
    }
}
