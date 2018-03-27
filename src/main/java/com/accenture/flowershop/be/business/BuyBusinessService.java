package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.order.Cart;
import com.accenture.flowershop.be.entity.order.CartItem;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface BuyBusinessService {

   // Flower cast();

//    OrderCustomer edit();

 //   boolean cast(String flowerName, Integer count, Cart cart);

  //  BigDecimal allSum( Cart cart);

  //  CartItem findCartItem (String nameFlower, Cart cart);

    OrderCustomer getOrderCustomer(Integer id);

    boolean saveOrderCustomer(String userName, Cart cart);

   // void flowerListClear();

    boolean editStatuseOrder(Integer orderCustomerId, String userName) ;


}
