package com.accenture.flowershop.be.business.impl;


import com.accenture.flowershop.be.access.OrderAccessService;
import com.accenture.flowershop.be.business.AdminBusinessService;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service("AdminBusinessServiceImpl")
public class AdminBusinessServiceImpl implements AdminBusinessService {

    @Autowired
    OrderAccessService orderAccessService;

   // List<OrderCustomer> orderList;

    @Override
    public List<OrderCustomer> getOrderList(){
       return this.orderAccessService.findAllOrderCustomer();
    }

    public AdminBusinessServiceImpl() {
    }

    @Override
    @Transactional
    public void editStatuse(Integer orderCustomerId, Integer n){
        OrderCustomer orderCustomer = this.orderAccessService.getOrderCustomer(orderCustomerId);
        orderCustomer.setStatus(n);
        this.orderAccessService.saveOrderCustomer(orderCustomer);
    }

    @PostConstruct
    public void test(){
        System.out.println("AdminBusinessServiceImpl создан! ");
    }
}
