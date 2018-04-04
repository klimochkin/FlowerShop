package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.business.impl.CatalogBusinessServiceImpl;
import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> cart = new ArrayList();

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public void cartClear(){
        this.cart.clear();
    }

    public List<CartItem> addCart(CartItem cartItem){
        this.cart.add(cartItem);
        return this.cart;
    }
    //------------------------------------------------------------------------------------------------------

    public Integer findCountFlowerToCart(Flower flower){

        for(CartItem cartItem : this.cart){
            if(cartItem.getNameFlower().equals(flower.getName()))
                return cartItem.getCountFlower();
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------

    public boolean cast( Flower flower, String flowerName, Integer count){

          //  Flower flower = catalogBusinessService.findFlower(flowerName);

            MathContext mc = new MathContext(4);
            BigDecimal sum = flower.getPrice().multiply(new BigDecimal(count), mc);
            CartItem cartItem = this.findCartItem(flowerName);

            //добавляем пункт заказа в корзину
            if(cartItem == null) {
                if(flower.getCount()>= count) {
                    cartItem = new CartItem(flowerName, count, sum);
                    this.addCart(cartItem);
                    // flower.setCount(flower.getCount()-count);
                }
                else
                    return false;
            }
            else {
                Integer newCount = cartItem.getCountFlower()+count;
                BigDecimal newSum = cartItem.getSumItem().add(sum);
                if(flower.getCount()>= newCount) {
                    cartItem.setCountFlower(newCount);
                    cartItem.setSumItem(newSum);
                    // flower.setCount(flower.getCount()-count);
                }
                else
                    return false;
            }
        return true;
    }

    //------------------------------------------------------------------------------------------------------


    public BigDecimal allSum() {

        BigDecimal allSum = BigDecimal.ZERO;
        for (CartItem item : this.getCart()){
            allSum = allSum.add(item.getSumItem());
        }
        return allSum;
    }

    public BigDecimal discountedSum(Integer discount) {

        BigDecimal allSum = BigDecimal.ZERO;
        for (CartItem item : this.getCart()){
            allSum = allSum.add(item.getSumItem());
        }
        allSum = allSum.subtract(allSum.multiply(new BigDecimal(discount)).divide(new BigDecimal(100)));
        return allSum;
    }


    //------------------------------------------------------------------------------------------------------

    public CartItem findCartItem (String nameFlower ){
        for (CartItem cartItem : this.getCart()){
            if (cartItem.getNameFlower().equals(nameFlower))
                return cartItem;
        }
        return null;
    }


}
