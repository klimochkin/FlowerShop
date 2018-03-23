package com.accenture.flowershop.be.entity.order;

import java.math.BigDecimal;

public class CartItem {

    private String nameFlower;

    private Integer countFlower;

    private BigDecimal sumItem;

    public CartItem(String nameFlower, Integer countFlower, BigDecimal sumItem) {
        this.nameFlower = nameFlower;
        this.countFlower = countFlower;
        this.sumItem = sumItem;
    }

    public String getNameFlower() {
        return nameFlower;
    }

    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
    }

    public Integer getCountFlower() {
        return countFlower;
    }

    public void setCountFlower(Integer countFlower) {
        this.countFlower = countFlower;
    }

    public BigDecimal getSumItem() {
        return sumItem;
    }

    public void setSumItem(BigDecimal sumItem) {
        this.sumItem = sumItem;
    }
}
