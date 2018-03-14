package com.accenture.flowershop.be.entity.order;

import java.util.HashSet;
import java.util.Set;

public class Cart {
    private int cart_id;
    private Set flowers = new HashSet();
    private int sum;
}
