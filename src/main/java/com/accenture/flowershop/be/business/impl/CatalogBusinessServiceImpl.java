package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.access.impl.FlowerAccessServiceImpl;
import com.accenture.flowershop.be.business.CatalogBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;

@Service("CatalogBusinessServiceImpl")
public class CatalogBusinessServiceImpl implements CatalogBusinessService {

    @Autowired
    private FlowerAccessService flowerAccessService;

    private List<Flower> listFlowers;

    public CatalogBusinessServiceImpl(){
    }


    @Override
    public List<Flower> findAllFlower() {
        if (listFlowers == null || listFlowers.size() == 0)
            this.listFlowers = this.flowerAccessService.findAll();
        return this.listFlowers;
    }

    @Override
    public Flower flowerFromList(String nameFlower){
        for(Flower flower : this.listFlowers) {
            if(flower.getName().equals(nameFlower))
                return flower;
        }
        return null;
    }


    @Override
    public boolean updateFlowerList() {
        try {
            for (Flower flower : this.flowerAccessService.findAll()) {
                Flower newFlower = this.flowerFromList(flower.getName());
                flower.setCount(newFlower.getCount());
                flowerAccessService.updateFlover(flower);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void flowerListClear(){
        this.listFlowers.clear();
    }

    @PostConstruct
    public void test(){
        System.out.println("CatalogBusinessServiceImpl создан! ");
    }
}
