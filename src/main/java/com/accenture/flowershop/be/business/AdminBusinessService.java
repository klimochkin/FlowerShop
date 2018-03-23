package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.order.OrderCustomer;

import java.util.List;

public interface AdminBusinessService {

    void editStatuse(Integer orderCustomerId, Integer n);

    List<OrderCustomer> getOrderList();
}
