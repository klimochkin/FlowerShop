package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.access.OrderAccessService;
import com.accenture.flowershop.be.business.BuyBusinessService;
import com.accenture.flowershop.be.business.CatalogBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Cart;
import com.accenture.flowershop.be.entity.order.CartItem;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
import com.accenture.flowershop.be.entity.order.OrderItem;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("BuyBusinessServiceImpl")
public class BuyBusinessServiceImpl implements BuyBusinessService {

    @Autowired
    private CustomerAccessService customerAccessService;

    @Autowired
    private CatalogBusinessService catalogBusinessService;

    @Autowired
    private OrderAccessService orderAccessService;

    public BuyBusinessServiceImpl(){
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public OrderCustomer getOrderCustomer(Integer id) {
        return this.orderAccessService.getOrderCustomer(id);
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public boolean saveOrderCustomer(String userName, Cart cart){
        OrderCustomer orderCustomer = new OrderCustomer();

        // Списание денег со счета юзера
        User user = this.customerAccessService.getUser(userName);

        // Получаем дату заказа
        Calendar currantDate = GregorianCalendar.getInstance();
        Date finalDate = currantDate.getTime();

        orderCustomer.setUser(user);
        orderCustomer.setSum(cart.allSum());

        orderCustomer.setStatus(OrderCustomer.OrderCustomerStatus.CREATED);
        orderCustomer.setDateOrder(finalDate);

        // Сохряняем заказ в базу
        this.orderAccessService.saveOrderCustomer(orderCustomer);

        // Сохраняем пункты заказа в базу
        OrderItem orderItem;
        for (CartItem cartItem : cart.getCart()){
            orderItem = new OrderItem();
            orderItem.setFlower(catalogBusinessService.findFlower(cartItem.getNameFlower()));
            orderItem.setCount(cartItem.getCountFlower());
            orderItem.setOrderCustomer(orderCustomer);
            this.orderAccessService.saveOrderItem(orderItem);
        }

        // Сохраняем в базу цветы с новыми данными о количестве на складе
        this.catalogBusinessService.updateFlowerList(cart);

        // Очищаем корзину
        cart.cartClear();


        return true;
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public boolean editStatuseOrder(Integer orderCustomerId, String userName) {
        OrderCustomer orderCustomer = this.orderAccessService.getOrderCustomer(orderCustomerId);

        // Списание денег со счета юзера
        User user = this.customerAccessService.getUser(userName);
        if(user.getAccount().compareTo(orderCustomer.getSum()) != -1){
            user.setAccount(user.getAccount().subtract(orderCustomer.getSum()));
            orderCustomer.setStatus(OrderCustomer.OrderCustomerStatus.PAID);
            this.customerAccessService.editCustomer(user);
            this.orderAccessService.updateOrderCustomer(orderCustomer);
            return true;
        }
        else
            return false;
    }

    @PostConstruct
    public void test(){
        System.out.println("BuyBusinessServiceImpl создан! ");
    }
}
