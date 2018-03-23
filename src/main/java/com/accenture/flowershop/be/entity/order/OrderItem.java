package com.accenture.flowershop.be.entity.order;

import com.accenture.flowershop.be.entity.flower.Flower;
import javax.persistence.*;


@Entity
@Table
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_OrderItem")
    @SequenceGenerator(name = "seq_OrderItem", sequenceName = "seq_OrderItem", allocationSize = 1)
    @Column(name = "orderItem_id")
    private Integer orderItemID;


    @ManyToOne
    @JoinColumn(name="flower_id")
    private Flower flower;


    @ManyToOne
    @JoinColumn(name="orderCustomer_id")
    private OrderCustomer orderCustomer;


    @Column
    private Integer count;

    public OrderItem() {
    }

    public OrderItem(Integer orderItemID, Flower flower, OrderCustomer orderCustomer, Integer count) {
        this.orderItemID = orderItemID;
        this.flower = flower;
        this.orderCustomer = orderCustomer;
        this.count = count;
    }

    public Integer getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(Integer orderItemID) {
        this.orderItemID = orderItemID;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public OrderCustomer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(OrderCustomer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

