package com.accenture.flowershop.be.entity.order;


import com.accenture.flowershop.be.entity.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class OrderCustomer {

    public enum OrderCustomerStatus { CREATED, PAID, CLOSED}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordCust")
    @SequenceGenerator(name = "seq_ordCust", sequenceName = "seq_ordCust", allocationSize = 1)
    @Column(name = "orderCustomer_id")
    private Integer orderCustomerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private BigDecimal sum;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderCustomerStatus status;

    @Column
    private Date dateOrder;


    //==========================================================================================


    public OrderCustomer() {
    }

    public OrderCustomer(Integer orderCustomerId, User user, BigDecimal sum, OrderCustomerStatus status, Date dateOrder) {
        this.orderCustomerId = orderCustomerId;
        this.user = user;
        this.sum = sum;
        this.status = status;
        this.dateOrder = dateOrder;
    }



    public Integer getOrderCustomerId() {
        return orderCustomerId;
    }

    public void setOrderCustomerId(Integer orderCustomerId) {
        this.orderCustomerId = orderCustomerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public OrderCustomerStatus getStatus() {
        return status;
    }

    public void setStatus(OrderCustomerStatus status) {
        this.status = status;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }
}
