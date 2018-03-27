package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;


@Service("UserBusinessServiceImpl")
public class UserBusinessServiceImpl implements UserBusinessService {

    @Autowired
    private CustomerAccessService customerAccessService;


    UserBusinessServiceImpl(){
    }



    @Override
    public User login(String login, String password) {
        return this.customerAccessService.loginUser(login, password);
    }

    @Override
    @Transactional
    public boolean register(String firstName, String lastName, String address, BigDecimal account, String tel, Integer discount, String username, String password, String email) {

    UserRole userRole = this.customerAccessService.getUserRole(2)    ;
    User user = new User( firstName,  lastName,  address,  account,  tel,  discount,  username,  password,  email, userRole);

        try {
        this.customerAccessService.saveCustomer(user);
    }catch (Exception e){
        System.out.println("Регистрация клиента не удалось! ");
        return false;
    }
        return true;
}

    public User findUser(String username){
        return this.customerAccessService.getUser(username);
    }

    @PostConstruct
    public void test(){
        System.out.println("UserBusinessServiceImpl создан! ");
    }
}
