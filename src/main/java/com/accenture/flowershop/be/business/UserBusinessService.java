package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.user.User;

public interface UserBusinessService {
    String login(String user, String password);
    User register(String user, String password, String address);

}
