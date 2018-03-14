package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.flower.Flower;

public interface BuyBusinessService {

    Flower cast();

//    Order edit();

    void save(Order order);
}
