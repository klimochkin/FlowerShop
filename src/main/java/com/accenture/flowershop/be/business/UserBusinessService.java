package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserRole;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.util.List;


public interface UserBusinessService {

    User login(String login, String password);


    boolean register(String firstName, String lastName, String address, BigDecimal account, String tel, Integer discount, String username, String password, String email) ;


 //  @WebResult(name = "user")
    User findUser(String username);

    List<User> findUsers();
}
