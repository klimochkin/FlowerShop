package com.accenture.flowershop.be.access.impl;

import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class FlowerAccessServiceImpl implements FlowerAccessService {

    FlowerAccessServiceImpl(){
    }

    @Override
    public List<Flower> findAll() {
        return null;
    }

    @PostConstruct
    public void test(){
        System.out.println("FlowerAccessServiceImpl создан! ");
    }
}
