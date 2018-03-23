package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.entity.order.CartItem;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface BuyBusinessService {

   // Flower cast();

//    OrderCustomer edit();
    List<CartItem> getCart();

    void cartClear();

    boolean cast(String flowerName, Integer count);

    BigDecimal allSum();

    CartItem findCartItem (String nameFlower);

    boolean saveOrderCustomer(String userName);

    void flowerListClear();


}
