package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class UserBusinessServiceImpl implements UserBusinessService {

    UserBusinessServiceImpl(){
    }


    @Override
    public String login(String user, String password) {
        return null;
    }


    @Override
    public User register(String user, String password, String address) {
        return null;
    }

    @PostConstruct
    public void test(){
        System.out.println("UserBusinessServiceImpl создан! ");
    }
}
