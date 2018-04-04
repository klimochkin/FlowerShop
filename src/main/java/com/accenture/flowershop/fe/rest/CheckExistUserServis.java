package com.accenture.flowershop.fe.rest;

import com.accenture.flowershop.be.entity.user.User;

import javax.ws.rs.core.Response;


public interface CheckExistUserServis {

 boolean checkUserList(String username);

}
