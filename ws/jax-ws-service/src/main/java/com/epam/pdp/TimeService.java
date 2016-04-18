package com.epam.pdp;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TimeService {

    @WebMethod
    Date getCurrentDate();

}
