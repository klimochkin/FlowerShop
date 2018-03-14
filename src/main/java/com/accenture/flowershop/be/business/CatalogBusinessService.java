package com.accenture.flowershop.be.business;


import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface CatalogBusinessService {

    List<Flower> viewCatalog();
    List<Flower> findFlower();
    void detailsFlower(Flower flower);
}
