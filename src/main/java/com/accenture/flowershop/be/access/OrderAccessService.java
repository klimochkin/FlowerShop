package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
import com.accenture.flowershop.be.entity.order.OrderItem;

import java.util.List;

public interface OrderAccessService {

     List<OrderCustomer> findAllOrderCustomer();

     List<OrderCustomer> findUserOrderCustomer(String username);

     OrderCustomer getOrderCustomer(Integer id);

     void saveOrderCustomer(OrderCustomer orderCustomer);

     OrderItem getOrderItem(Integer id);

     void updateOrderCustomer(OrderCustomer orderCustomer);

     void saveOrderItem(OrderItem orderItem);
}
