package com.accenture.flowershop.be.access.impl;

import com.accenture.flowershop.be.access.FlowerAccessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class FlowerAccessServiceImpl implements FlowerAccessService {

    @PersistenceContext
    private EntityManager entityManager;

    //String strQuery="select name from Flower f where some_col=-1";


    @PostConstruct
    public void test() {
        System.out.println("FlowerAccessServiceImpl создан! ");
    }

    public FlowerAccessServiceImpl() {
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public List<Flower> findAll() {
        return entityManager.createQuery("select f from Flower f", Flower.class).getResultList();
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public Flower getFlower(String name) {

        TypedQuery<Flower> tq = entityManager.createQuery(
                "Select f from Flower f where f.name = :name", Flower.class);
        tq.setParameter("name", name);

        Flower result = null;
        try {
            result = tq.getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println("Цветок - " + name + " - не найден!!!");
            return null;
        }
    }

    //------------------------------------------------------------------------------------------------------
    @Override
    public void updateFlover(Flower flower) {
        try {
            this.entityManager.merge(flower);
            //   EntityManager.flush();
        } catch (Exception e) {
            System.out.println("Ошибка записи юзера " + flower.getName() + " в БД");
        }
    }


    //------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void saveFlower(Flower flower) {
        try {
            this.entityManager.persist(flower);
            //   EntityManager.flush();
        } catch (Exception e) {
            System.out.println("Ошибка записи юзера " + flower.getName() + " в БД");
        }
    }


    @Override
    public List<Flower> findFlowers(String flowerName, BigDecimal min, BigDecimal max) {


        TypedQuery<Flower> tq;

        if (flowerName.equals("")){
            tq = entityManager.createQuery(
                    "select f from Flower f where f.price BETWEEN :min and :max", Flower.class);
            tq.setParameter("min", min);
            tq.setParameter("max", max);
        }
        else {
            tq = entityManager.createQuery(
                    "select f from Flower f where f.name = :name and f.price BETWEEN :min and :max", Flower.class);
            tq.setParameter("name", flowerName);
            tq.setParameter("min", min);
            tq.setParameter("max", max);
        }

        List<Flower> result = null;
        try {
            result = tq.getResultList();
            return result;
        } catch (Exception e) {
            System.out.println("Список цветов не найден!!!");
            return null;
        }
    }
}