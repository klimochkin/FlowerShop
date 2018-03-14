package com.accenture.flowershop.be.access.impl;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.entity.user.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerAccessServiceImpl implements CustomerAccessService {

    private String str;

    public CustomerAccessServiceImpl(String str) {
        this.str = str;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    public void test(){

        System.out.println("");
    }
}
