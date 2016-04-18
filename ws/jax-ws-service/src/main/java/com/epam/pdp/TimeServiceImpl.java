package com.epam.pdp;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;

@WebService(endpointInterface = "com.epam.pdp.TimeService")
public class TimeServiceImpl implements TimeService {

    @WebMethod(operationName = "currentDate")
    public Date getCurrentDate() {
        return new Date();
    }

}
