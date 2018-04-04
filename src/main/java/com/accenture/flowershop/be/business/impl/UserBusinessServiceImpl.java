package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.access.CustomerAccessService;
import com.accenture.flowershop.be.business.JMS.JmsConsumer;
import com.accenture.flowershop.be.business.JMS.JmsProducer;
import com.accenture.flowershop.be.business.UserBusinessService;
import com.accenture.flowershop.be.business.UserMarshgallingService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.be.entity.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jws.WebService;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@WebService
@Service("UserBusinessServiceImpl")
public class UserBusinessServiceImpl implements UserBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Autowired
    private CustomerAccessService customerAccessService;

    @Autowired
    private UserMarshgallingService userMarshgallingService;

    @Autowired
    private JmsProducer producer;

    @Autowired
    private JmsConsumer consumer;


    UserBusinessServiceImpl(){
    }


    @Override
    public User login(String login, String password) {
        return this.customerAccessService.loginUser(login, password);
    }

    @Override
    @Transactional
    public boolean register(String firstName, String lastName, String address, BigDecimal account, String tel, Integer discount, String username, String password, String email) {

        UserRole userRole = this.customerAccessService.getUserRole(2);
        User user = new User(firstName, lastName, address, account, tel, discount, username, password, email, userRole);


        String url = "tcp://localhost:61616";
        try {

            this.userMarshgallingService.convertFromObjectToXML(user, "user.xml");

            producer.send();

            String text = null;
            Message msg = consumer.receiveMSG(10000);
            if (msg != null && msg instanceof TextMessage) {
                text = ((TextMessage) msg).getText();
                LOG.info(String.format("Получено сообщение: ", text));
            } else {
                LOG.info(String.format("Не получено сообщение: '%s'", msg));
                LOG.info(String.format("Не получено сообщение: ", msg));
            }
            System.out.println("println"+text);


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            this.customerAccessService.saveCustomer(user);
            return true;
        } catch (Exception e) {
            System.out.println("Регистрация клиента не удалось! ");
            return false;
        }
    }


    @Override
    public User findUser(String username){
          return this.customerAccessService.getUser(username);
    }

    @Override
    public List<User> findUsers(){
        return customerAccessService.findAll();
    }

    @PostConstruct
    public void test(){
        System.out.println("UserBusinessServiceImpl создан! ");
    }
}
