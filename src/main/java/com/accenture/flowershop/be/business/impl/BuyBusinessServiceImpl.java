package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.business.BuyBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;

import javax.annotation.PostConstruct;


public class BuyBusinessServiceImpl implements BuyBusinessService {

    public BuyBusinessServiceImpl(){
    }

    @Override
    public Flower cast(){

        return null;
    }

//    Order edit();


    @Override
    public void save(Order order){

    }

    @PostConstruct
    public void test(){
        System.out.println("BuyBusinessServiceImpl создан! ");
    }
}
