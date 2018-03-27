package com.accenture.flowershop.be.access.impl;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.access.OrderAccessService;
import com.accenture.flowershop.be.entity.order.OrderCustomer;
import com.accenture.flowershop.be.entity.order.OrderItem;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderAccessServiceImpl implements OrderAccessService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    CustomerAccessService customerAccessService;

    OrderAccessServiceImpl(){
    }

    @PostConstruct
    public void test(){
        System.out.println("OrderAccessServiceImpl создан! ");
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public List<OrderCustomer> findAllOrderCustomer() {
        return entityManager.createQuery("select oc from OrderCustomer oc", OrderCustomer.class).getResultList();
    }

    @Override
    public List<OrderCustomer> findUserOrderCustomer(String username) {
        User user = customerAccessService.getUser(username);
        Integer id  = user.getId();
        TypedQuery<OrderCustomer> tq = entityManager.createQuery("select oc from OrderCustomer oc where oc.user.id = :id", OrderCustomer.class);
        tq.setParameter("id", id);
        return tq.getResultList();
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    public OrderCustomer getOrderCustomer(Integer id){

        TypedQuery<OrderCustomer> tq = entityManager.createQuery(
                "Select oc from OrderCustomer oc where oc.id = :id", OrderCustomer.class);
        tq.setParameter("id", id);


        OrderCustomer result = null;
        try {
            result = tq.getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println("Цветок - "+id+" - не найден!!!");
            return null;
        }
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void saveOrderCustomer(OrderCustomer orderCustomer){
        try {
            this.entityManager.persist(orderCustomer);
            //   EntityManager.flush();
        }catch (Exception e) {
            System.out.println("Ошибка записи заказа "+orderCustomer.getSum()+" в БД");
        }
    }

    //------------------------------------------------------------------------------------------------------

    public OrderItem getOrderItem(Integer id){

        TypedQuery<OrderItem> tq = entityManager.createQuery(
                "Select oi from OrderItem oi where oi.id = :id", OrderItem.class);
        tq.setParameter("id", id);


        OrderItem result = null;
        try {
            result = tq.getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println("Цветок - "+id+" - не найден!!!");
            return null;
        }
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void updateOrderCustomer(OrderCustomer orderCustomer) {
        try {
            this.entityManager.merge(orderCustomer);
            //   EntityManager.flush();
        }catch (Exception e) {
            System.out.println("Ошибка записи заказа "+orderCustomer.getSum()+" в БД");
        }
    }

    //------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void saveOrderItem(OrderItem orderItem){
        try {
            this.entityManager.persist(orderItem);
            //   EntityManager.flush();
        }catch (Exception e) {
            System.out.println("Ошибка записи пункта заказа "+ orderItem.getFlower().getName()+" в БД");
        }
    }
}
