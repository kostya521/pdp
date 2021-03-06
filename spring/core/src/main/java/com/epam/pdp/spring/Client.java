package com.epam.pdp.spring;

public class Client {
    private String id;
    private String greeting;

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Client(String id, String fullName, String greeting) {
        this.id = id;
        this.fullName = fullName;
        this.greeting = greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return greeting;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private String fullName;

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", greeting='" + greeting + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
