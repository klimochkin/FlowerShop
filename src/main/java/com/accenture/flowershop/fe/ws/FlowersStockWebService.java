package com.accenture.flowershop.fe.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FlowersStockWebService {

    @WebMethod
    void increaseFlowersStockSize(@WebParam(name = "countFlower")int count);
}
