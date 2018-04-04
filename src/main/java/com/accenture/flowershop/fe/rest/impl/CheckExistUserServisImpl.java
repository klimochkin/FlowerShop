package com.accenture.flowershop.fe.rest.impl;

import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.business.impl.UserBusinessServiceImpl;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.rest.CheckExistUserServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Component
@Path("/checkuser")
public class CheckExistUserServisImpl implements CheckExistUserServis {

    @Autowired
    UserBusinessService userBusinessService;

    public CheckExistUserServisImpl() {
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/check/{name}")
    @Override
    public boolean checkUserList(@PathParam("name") String username) {
        User user = userBusinessService.findUser(username);

        return !(user == null);
           // return Response.ok().build();
       // return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

}


//    for( User userItem : this.userBusinessService.findUsers()){
//        if(userItem.getUsername().equals(user.getUsername())){
//            return true;
//        }
//    }