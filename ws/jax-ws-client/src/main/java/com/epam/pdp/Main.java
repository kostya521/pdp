package com.epam.pdp;

import com.epam.pdp.ws.TimeService;
import com.epam.pdp.ws.TimeServiceImplService;

import javax.xml.datatype.XMLGregorianCalendar;

public class Main {
    public static void main(String[] args) {
        TimeServiceImplService timeService = new TimeServiceImplService();
        TimeService timeServiceImplPort = timeService.getTimeServiceImplPort();

        XMLGregorianCalendar currentDate = timeServiceImplPort.getCurrentDate();
        System.out.println(currentDate.toString());
    }
}
