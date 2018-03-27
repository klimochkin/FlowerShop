package com.accenture.flowershop.be.business;


import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Cart;

import java.util.List;

public interface CatalogBusinessService {

    List<Flower> findAllFlower();

    Flower findFlower(String nameFlower);

    boolean updateFlowerList( Cart cart);

 //   void flowerListClear();

    List<Flower> findListFlower(String flowerName, Integer min, Integer max);
}
