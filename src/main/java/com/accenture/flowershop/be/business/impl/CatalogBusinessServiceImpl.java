package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.business.CatalogBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CatalogBusinessServiceImpl implements CatalogBusinessService {

    public CatalogBusinessServiceImpl(){
    }

    @Override
    public List<Flower> viewCatalog() {
        return null;
    }

    @Override
    public List<Flower> findFlower() {
        return null;
    }

    @Override
    public void detailsFlower(Flower flower) {

    }

    @PostConstruct
    public void test(){
        System.out.println("CatalogBusinessServiceImpl создан! ");
    }
}
