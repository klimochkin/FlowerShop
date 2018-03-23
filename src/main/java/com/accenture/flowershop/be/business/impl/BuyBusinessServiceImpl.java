package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.access.OrderAccessService;
import com.accenture.flowershop.be.business.BuyBusinessService;
import com.accenture.flowershop.be.business.CatalogBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
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

    private List<CartItem> cart = new ArrayList();

    public List<CartItem> getCart() {
        return cart;
    }

    public void cartClear(){
        this.cart.clear();
    }

  //  @Autowired
 //   private FlowerAccessService flowerAccessService;

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
    public boolean cast(String flowerName, Integer count){

        try {
            Flower flower = catalogBusinessService.flowerFromList(flowerName);
            MathContext mc = new MathContext(4);
            BigDecimal sum = flower.getPrice().multiply(new BigDecimal(count), mc);

            CartItem cartItem = this.findCartItem(flowerName);

            if(cartItem == null) {
                if(flower.getCount()>= count) {
                    cartItem = new CartItem(flowerName, count, sum);
                    this.cart.add(cartItem);
                    flower.setCount(flower.getCount()-count);
                }
                else
                    return false;
            }
            else {
                Integer newCount = cartItem.getCountFlower()+count;
                BigDecimal newSum = cartItem.getSumItem().add(sum);
                if(flower.getCount()>= count) {
                    cartItem.setCountFlower(newCount);
                    cartItem.setSumItem(newSum);
                    flower.setCount(flower.getCount()-count);
                }
                else
                    return false;
            }
          // User user = this.customerAccessService.getUser(userName);
          //  user.setAccount(user.getAccount().subtract(sum));

        }catch (Exception e){
            System.out.println("Ошибка броска в корзину");
        }
        return true;
    }

    //------------------------------------------------------------------------------------------------------

    public BigDecimal allSum(){

        BigDecimal allSum = new BigDecimal("0");
        for (CartItem item : this.getCart()){
            allSum = allSum.add(item.getSumItem());
        }

        return allSum;
    }

    //------------------------------------------------------------------------------------------------------

    public CartItem findCartItem (String nameFlower){
        for (CartItem cartItem : this.getCart()){
            if (cartItem.getNameFlower().equals(nameFlower))
                return cartItem;
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public boolean saveOrderCustomer(String userName){
        OrderCustomer orderCustomer = new OrderCustomer();

        // Списание денег со счета юзера
        User user = this.customerAccessService.getUser(userName);
        if(user.getAccount().compareTo(this.allSum()) != -1){
            user.setAccount(user.getAccount().subtract(this.allSum()));
            this.customerAccessService.editCustomer(user);
        }
        else
            return false;

        // Получаем дату заказа
        Calendar currantDate = GregorianCalendar.getInstance();
        Date finalDate = currantDate.getTime();

        orderCustomer.setUser(user);
        orderCustomer.setSum(this.allSum());
        orderCustomer.setStatus(0);
        orderCustomer.setDateOrder(finalDate);

        // Сохряняем заказ в базу
        this.orderAccessService.saveOrderCustomer(orderCustomer);

        // Сохраняем пункты заказа в базу
        OrderItem orderItem;
        for (CartItem cartItem : this.getCart()){
            orderItem = new OrderItem();
            orderItem.setFlower(catalogBusinessService.flowerFromList(cartItem.getNameFlower()));
            orderItem.setCount(cartItem.getCountFlower());
            orderItem.setOrderCustomer(orderCustomer);
            this.orderAccessService.saveOrderItem(orderItem);
        }

        // Очищаем корзину
        this.cartClear();

        // Сохраняем в базу цветы с новыми данными о количестве на складе
        this.catalogBusinessService.updateFlowerList();

        return true;
    }

    //------------------------------------------------------------------------------------------------------

    public void flowerListClear(){
        catalogBusinessService.flowerListClear();
    }

    @PostConstruct
    public void test(){
        System.out.println("BuyBusinessServiceImpl создан! ");
    }
}
