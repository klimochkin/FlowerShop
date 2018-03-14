package com.accenture.flowershop.be.entity.order;

public class Order {

    private int cart_id;
    private int flower_id;

    public Order(int cart_id, int flower_id) {
        this.cart_id = cart_id;
        this.flower_id = flower_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getFlower_id() {
        return flower_id;
    }

    public void setFlower_id(int flower_id) {
        this.flower_id = flower_id;
    }
}
