package com.accenture.flowershop.be.business.impl;


import com.accenture.flowershop.be.access.OrderAccessService;
import com.accenture.flowershop.be.business.AdminBusinessService;
import com.accenture.flowershop.be.entity.order.OrderCustomer;

//import org.jboss.logging.Logger;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service("AdminBusinessServiceImpl")
public class AdminBusinessServiceImpl implements AdminBusinessService {


    private static final Logger LOG = LoggerFactory.getLogger(AdminBusinessServiceImpl.class);


    @Autowired
    OrderAccessService orderAccessService;

   // List<OrderCustomer> orderList;

    @Override
    public List<OrderCustomer> getOrderList(){
       return this.orderAccessService.findAllOrderCustomer();
    }

    @Override
    public List<OrderCustomer> getOrderList(String username) {

       return orderAccessService.findUserOrderCustomer(username);
    }

    public AdminBusinessServiceImpl() {
    }

    @Override
    @Transactional
    public void editStatuse(Integer orderCustomerId){
        OrderCustomer orderCustomer = this.orderAccessService.getOrderCustomer(orderCustomerId);
        orderCustomer.setStatus(OrderCustomer.OrderCustomerStatus.CLOSED);
        this.orderAccessService.updateOrderCustomer(orderCustomer);
    }

    @PostConstruct
    public void test(){
        System.out.println("AdminBusinessServiceImpl создан! ");

        LOG.debug("Создан бин AdminBusinessServiceImpl");
        LOG.error("test error");
    }
}
