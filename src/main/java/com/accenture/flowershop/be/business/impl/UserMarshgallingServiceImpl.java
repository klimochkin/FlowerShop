package com.accenture.flowershop.be.business.impl;

import com.accenture.flowershop.be.business.UserMarshgallingService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@Service("UserMarshgallingServiceImpl")
public class UserMarshgallingServiceImpl implements UserMarshgallingService {

    @Autowired
    private CastorMarshaller marshaller;

   // private Marshaller marshaller;

    private Unmarshaller unmarshaller;


    public CastorMarshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(CastorMarshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public UserMarshgallingServiceImpl() throws JAXBException {
    }



    @Override
    public void convertFromObjectToXML( User user, String filepath) throws IOException {
        try {
            File file = new File(filepath);

           // marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FileOutputStream os = new FileOutputStream(filepath);
            getMarshaller().marshal(user, new StreamResult(os));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не удалось записать данные в файл!");
        }
    }


    public User convertFromXMLToObject(String filepath) throws IOException {

        try {


            FileInputStream is = new FileInputStream(filepath);
            return (User) getUnmarshaller().unmarshal(new StreamSource(is));

        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("Не удалось считать данные файла!");
        }
        return null;
    }

    @PostConstruct
    public void test(){
        System.out.println("UserMarshgallingServiceImpl создан! ");
    }
}
