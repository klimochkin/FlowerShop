package com.accenture.flowershop.be.business.JMS;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;


@Service
public class JmsConsumer implements AutoCloseable
{

    private QueueConnection queueConnection = null;


//------------------------------------------------------------------------------------------------------

    public JmsConsumer(){

    }

//------------------------------------------------------------------------------------------------------
/*
    public void init() throws JMSException, NamingException {
        System.out.println("Init consumer...");

        // создаем фабрику коннектов
        InitialContext initCtx = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) initCtx.lookup("java:comp/env/jms/connectionFactory");

        //создаем коннект и стартуем его
        connection = connectionFactory.createConnection();
        connection.start();

        // создаем сессию
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // создаем целевой объект
        Destination destination = session.createQueue(queueName);
       // MessageConsumer consumer  = session.createConsumer(inQueue);

        // создаем обьект получателя
        MessageConsumer consumer = session.createConsumer(destination);

       // consumer.receive(10000); //setMessageListener(this); // подписываемся на событие onMessage

        System.out.println("Получатель проинициализирован");
    }
*/

    @PostConstruct
    public void init() throws JMSException, NamingException {
        System.out.println("Init consumer...");

        // создаем фабрику коннектов
        InitialContext initCtx = new InitialContext();
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initCtx
                .lookup("java:comp/env/jms/connectionFactory");

        System.out.println("Создание приемника...");

        this.queueConnection = connectionFactory.createQueueConnection();
        this.queueConnection.start();
        System.out.println("Приемник успешно создан");
    }

//------------------------------------------------------------------------------------------------------


    public Message receiveMSG(long ms) throws JMSException, NamingException {
           InitialContext initCtx = new InitialContext();
           QueueSession queueSession =  this.queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
           Queue queue = (Queue) initCtx.lookup("java:comp/env/jms/outQueue");
           QueueReceiver queueReceiver = queueSession.createReceiver(queue);
           Message msg = queueReceiver.receive(ms);

        try
        {
            if (queueSession != null)
                queueSession.close();
        }
        catch (JMSException jmsEx)
        {
            jmsEx.printStackTrace();
        }
           return  msg;
    }

//------------------------------------------------------------------------------------------------------
/*
    public void messageToFile( String filePath){
        File file = new File(filePath);

        try(FileWriter writer = new FileWriter(filePath, false)){
            String textXML =  ((TextMessage) this.getMsg()).getText();
            writer.write(textXML);
            writer.flush();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return file;
    }
*/
//------------------------------------------------------------------------------------------------------

    @PreDestroy
    public void close() throws Exception {
        try {
            if (queueConnection != null)
                queueConnection.close();
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
