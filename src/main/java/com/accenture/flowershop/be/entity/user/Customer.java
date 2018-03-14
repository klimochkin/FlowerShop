package com.accenture.flowershop.be.entity.user;

import com.accenture.flowershop.be.entity.order.Cart;

public class Customer {
    private int customer_id;
    private String firstName;
    private String lastName;
    private String address;
    private int account;
    private int tel;
    private Cart cart;
    private User user;



    public Customer(int id, String firstName, String lastName, String address, int account, int tel, Cart cart, User user) {
        this.customer_id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.account = account;
        this.tel = tel;
        this.cart = cart;
        this.user = user;
    }


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }



}
