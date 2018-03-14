package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.user.Customer;

import java.util.List;

public interface CustomerAccessService {
    public List<Customer> findAll();
}
