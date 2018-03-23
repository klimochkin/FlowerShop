package com.accenture.flowershop.be.access.impl;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserRole;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class CustomerAccessServiceImpl implements CustomerAccessService {

    /*
//------------------------------------------------------------------------------------------------------
    private Map<String, User> emulDB = new HashMap<String, User>();

    public Map<String, User> getEmulDB() {
        return emulDB;
    }

    public void setEmulDB(String userName, User cust) {

        this.emulDB.put(userName, cust) ;
    }

    public User login(String str){
       return this.getEmulDB().get(str);
    }
//------------------------------------------------------------------------------------------------------
*/
    @PersistenceContext
    private EntityManager EntityManager;

    public CustomerAccessServiceImpl(){
     }


    @PostConstruct
    public void test(){
        System.out.println("CustomerAccessServiceImpl создан! ");
    }

//------------------------------------------------------------------------------------------------------

    public UserRole getUserRole(int id) {
        TypedQuery<UserRole> tq = EntityManager.createQuery("Select r from UserRole r where r.userTypeId = :id", UserRole.class);
        tq.setParameter("id", id);

        UserRole result = null;
        try {
            result = tq.getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println(" Инстанс UserRole не найден!!!");
            return null;
        }
    }

//------------------------------------------------------------------------------------------------------

    public User getUser(Integer id){

        TypedQuery<User> tq = EntityManager.createQuery(
                "Select user from User user where user.user_id = :id", User.class);
        tq.setParameter("id", id);

        User result = null;
        try {
            result = tq.getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println("User - "+id+" - не найден!!!");
            return null;
        }
    }

//------------------------------------------------------------------------------------------------------

    public User getUser(String userName){
        TypedQuery<User> tq = EntityManager.createQuery(
                "Select user from User user where user.username = :userName", User.class);
        tq.setParameter("userName", userName);

        User result = null;
        try {
            result = tq.getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println("User - "+userName+" - не найден!!!");
            return null;
        }
    }

//------------------------------------------------------------------------------------------------------
    @Override
    public User loginUser(String login, String password){
         TypedQuery<User> tq = EntityManager.createQuery(
                 "Select user from User user where user.username = :login and user.password = :password ", User.class);
         tq.setParameter("login", login);
         tq.setParameter("password", password);

         User result = null;
         try {
             result = tq.getSingleResult();
             return result;
         } catch (Exception e) {
             System.out.println("User - "+login+" - "+password+" - не найден!!!");
             return null;
         }
    }

//------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void saveCustomer(User user){
        try {
            this.EntityManager.persist(user);
         //   EntityManager.flush();
        }catch (Exception e) {
            System.out.println("Ошибка записи юзера "+user.getFirstName()+" в БД");
        }
    }

//------------------------------------------------------------------------------------------------------

    @Override
    @Transactional
    public void  editCustomer(User user){
        try {
          //  User user = this.getUser(id);

            this.EntityManager.merge(user);
            //   EntityManager.flush();
        }catch (Exception e) {
            System.out.println("Ошибка записи юзера "+user.getUsername()+" в БД");
        }
    }
}
