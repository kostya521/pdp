package com.epam.pdp;

import javax.xml.ws.Endpoint;

public class ServicePublisher {
    private static final String URL = "http://localhost:5555/ws/getdate";

    public static void main(String[] args) {
        System.out.println("Starting TimeService");
        Endpoint.publish(URL, new TimeServiceImpl());
        System.out.println("Started TimeService");
    }
}
