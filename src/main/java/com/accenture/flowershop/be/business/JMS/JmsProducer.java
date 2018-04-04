package com.accenture.flowershop.be.business.JMS;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;


@Service
public class JmsProducer implements AutoCloseable{

    private static String IN_QUEUE = "IN_QUEUE";

  //  @Resource(lookup = "java:comp/env/jms/ConnectionFactory")
  //  private ActiveMQConnectionFactory connectionFactory;

    private Connection connection = null;



    public JmsProducer() {
    }

    public JmsProducer(String url) {
        // создаем фабрику коннектов
      //  connectionFactory = new ActiveMQConnectionFactory(url);

    }

//------------------------------------------------------------------------------------------------------

    @PostConstruct
    private void init() throws JMSException, NamingException {

        // создаем фабрику коннектов
        InitialContext initCtx = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) initCtx.lookup("java:comp/env/jms/connectionFactory");



        //создаем коннект и стартуем его
        connection = connectionFactory.createConnection();
        connection.start();

        System.out.println("Producer JMS успешно создан");
    }


//------------------------------------------------------------------------------------------------------

    public void send(){
        try{
            // создаем сессию
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // создаем целевой объект
            Destination destination = session.createQueue(IN_QUEUE);

            //объект producer-а для него
            MessageProducer messageProducer = session.createProducer(destination);


            FileInputStream inFile = new FileInputStream("user.xml");
            byte[] str = new byte[inFile.available()];
            inFile.read(str);
            String userXML = new String(str);


            Message msg = session.createTextMessage(userXML);
            msg.setObjectProperty("Created", (new Date()).toString());

            messageProducer.send(msg);
            System.out.println("Сообщение отправлено");

            try
            {
                if (session != null)
                    session.close();
            }
            catch (JMSException jmsEx)
            {
                jmsEx.printStackTrace();
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

//------------------------------------------------------------------------------------------------------

    @PreDestroy
    public void close(){
        if (connection != null){
            try{
                connection.close();
            }
            catch (JMSException e){
                e.printStackTrace();
            }
        }
    }
}
