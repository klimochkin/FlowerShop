package com.accenture.flowershop.be.access;

import com.accenture.flowershop.be.entity.flower.Flower;

import java.util.List;

public interface FlowerAccessService {
    public List<Flower> findAll();
}
