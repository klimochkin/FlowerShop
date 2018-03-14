package com.accenture.flowershop.be.entity.flower;

import java.math.BigDecimal;

public class Flower {
    private Long flowerId;
    private String name;
    private BigDecimal price;

    public Flower(int flower_id, String name, BigDecimal price) {
     //   this.flower_id = flower_id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return flowerId;
    }

    public void setId(Long id) {
        this.flowerId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
