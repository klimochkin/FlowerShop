package com.accenture.flowershop.be.access;


import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserRole;

import java.util.List;

public interface CustomerAccessService {

    // возвращает найденого юзера по логину и паролю, либо null
    User loginUser(String login, String password);

    User getUser(Integer id);

    User getUser(String userName);

    // сохранение юзера в базу
    void saveCustomer(User user);

    //получение инстанса роли
    UserRole getUserRole(int id);

    void  editCustomer (User user);

    List<User> findAll();
}
