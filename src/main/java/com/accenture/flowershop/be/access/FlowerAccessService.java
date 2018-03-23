package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerAccessService {
     List<Flower> findAll();

     Flower getFlower(String name);

     void saveFlower(Flower flower);

     void updateFlover(Flower flower);
}
