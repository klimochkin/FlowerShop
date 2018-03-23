package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserRole;

import java.math.BigDecimal;

public interface UserBusinessService {

    User login(String login, String password);

    boolean register(String firstName, String lastName, String address, BigDecimal account, String tel, Integer discount, String username, String password, String email) ;

    User findUser(String username);
}
