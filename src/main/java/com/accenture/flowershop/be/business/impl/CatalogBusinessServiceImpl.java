package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.access.impl.FlowerAccessServiceImpl;
import com.accenture.flowershop.be.business.CatalogBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Service("CatalogBusinessServiceImpl")
public class CatalogBusinessServiceImpl implements CatalogBusinessService {

    @Autowired
    private FlowerAccessService flowerAccessService;

    public CatalogBusinessServiceImpl(){
    }

    @Override
    public List<Flower> findAllFlower() {
        return  this.flowerAccessService.findAll();
    }

    @Override
    public Flower findFlower(String nameFlower){
                return   this.flowerAccessService.getFlower(nameFlower);
   }


    @Override
    public boolean updateFlowerList( Cart cart) {

        try {
            for (Flower flower : this.flowerAccessService.findAll()) {

                Integer count = cart.findCountFlowerToCart(flower);
                if(count != null){
                    flower.setCount(flower.getCount()-count);
                    flowerAccessService.updateFlover(flower);
                }
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }


        @Override
        public List<Flower> findListFlower(String flowerName, Integer min, Integer max){
            if(min == null)
                min = 0;
            if(max == null)
                max = Integer.MAX_VALUE;

            BigDecimal minB = new BigDecimal(min);
            BigDecimal maxB = new BigDecimal(max);


        return this.flowerAccessService.findFlowers(flowerName, minB, maxB);
    }

    @PostConstruct
    public void test(){
        System.out.println("CatalogBusinessServiceImpl создан! ");
    }
}
