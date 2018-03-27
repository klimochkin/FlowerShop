package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.math.BigDecimal;
import java.util.List;

public interface FlowerAccessService {
     List<Flower> findAll();

     Flower getFlower(String name);

     void saveFlower(Flower flower);

     void updateFlover(Flower flower);

     List<Flower> findFlowers(String flowerName, BigDecimal min, BigDecimal max);
}
