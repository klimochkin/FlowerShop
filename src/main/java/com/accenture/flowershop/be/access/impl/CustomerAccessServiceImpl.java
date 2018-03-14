package com.accenture.flowershop.be.access.impl;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.entity.user.Customer;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CustomerAccessServiceImpl implements CustomerAccessService {

    public CustomerAccessServiceImpl(){
    }


    @Override
    public List<Customer> findAll() {
        return null;
    }

    @PostConstruct
    public void test(){
        System.out.println("CustomerAccessServiceImpl создан! ");
    }
}
