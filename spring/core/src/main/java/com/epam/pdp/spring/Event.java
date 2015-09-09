package com.epam.pdp.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private DateFormat df;
    private int id = new Random().nextInt();
    private Date date;
    private String message;

    public Event() {
    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("id: %s date: %s message: %s", id, df.format(date), message);
    }
}
