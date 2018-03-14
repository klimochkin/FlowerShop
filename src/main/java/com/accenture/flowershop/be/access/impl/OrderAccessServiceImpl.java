package com.accenture.flowershop.be.access.impl;

import com.accenture.flowershop.be.access.OrderAccessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class OrderAccessServiceImpl implements OrderAccessService {

    OrderAccessServiceImpl(){
    }

    @Override
    public List<Flower> findAll() {
        return null;
    }


    @PostConstruct
    public void test(){
        System.out.println("OrderAccessServiceImpl создан! ");
    }
}
