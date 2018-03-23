package com.accenture.flowershop.be.entity.flower;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "flower")
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_flower4")
    @SequenceGenerator(name = "seq_flower4", sequenceName = "seq_flower", allocationSize = 1)
    @Column(name = "flower_id")
    private Long flowerId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "count")
    private Integer count;


    //@OneToMany(mappedBy="flower", fetch = FetchType.LAZY)
    //private Set<OrderCustomer> order = new HashSet();


    public Flower() {
    }

    public Flower(Long flowerId, String name, BigDecimal price, int count) {
        this.flowerId = flowerId;
        this.name = name;
        this.price = price;
        this.count = count;
       // this.addOrder(myOrder);
    }

    public Long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

 //   public  void addOrder(OrderCustomer myOrder)   {
 //       this.order.add(myOrder);
//        myOrder.setFlower(this);	// Bidirectional consistency should be managed programmatically
 //   }

}
