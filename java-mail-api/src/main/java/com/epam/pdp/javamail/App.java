package com.epam.pdp.javamail;

import javax.mail.Message;

public class App {


    public static void main(String[] args) {
        EmailClient emailClient = new EmailClient();

        Message[] messages = emailClient.getMessages("Inbox");

        
    }
}
