package com.accenture.flowershop.be.DAO;

import com.accenture.flowershop.be.entity.flower.Flower;
//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerDAO {

       // @SuppressWarnings("unused")
        private Connection conn;

        private List<Flower> listFlower = new ArrayList<>();


        public List<Flower> getListFlower() {
            return listFlower;
        }

        public void setListAccount(List<Flower> listFlower) {
            this.listFlower = listFlower;
        }


        public FlowerDAO(Connection conn) {
            this.conn = conn;
        }
/*
        public List<Flower> findListFlower(String name)
                throws SQLException {
            try {
                Statement statement = null;
                statement = this.conn.createStatement();

             //   ResultSet result = statement.executeQuery("SELECT * FROM Flower WHERE name = '" + name + "' OR category = '" + category + "' ORDER BY id");
                ResultSet result = statement.executeQuery("SELECT * FROM Flower WHERE name = '" + name +"'");
                while (result.next()) {
                    Flower account = new Flower(	result.getInt("id"),
                            result.getString("name"),
                            result.getString("category"),
                            result.getInt("price")
                    );
                    this.getListFlower().add(account);
                }

            }catch (SQLException e1){
                e1.getMessage();
            }
            return this.getListFlower();
        }
===================================
        public Flower findFlower (int id) throws SQLException {

            Statement statement = null;
            statement = this.conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM ACCOUNT WHERE ID = '"+id+"'");

            Flower account = null;
            if (result.next()){
                account = new Flower(result.getInt("id"),
                        result.getString("FIRST_NAME"),
                        result.getString("LAST_NAME"),
                        result.getString("E_MAIL")
                );
            }
            return account;
        }


        public boolean insertFlower(String name, String category, int price)
        throws SQLException {
            Statement statement = null;
            statement = this.conn.createStatement();
            statement.executeUpdate("Insert into flower (name, category, price) values ('" +name+ "', '"+category+"','" +price+"')");

            ResultSet result = statement.executeQuery("SELECT * FROM Flower WHERE name = '" + name + "' AND category = '" + category+"'");

            if (result.next())
                return true;
            return false;
        }

    public void deleteFlower(int id) throws SQLException {

        Statement statement = null;
        statement = this.conn.createStatement();
        statement.execute("DELETE FROM flower WHERE id = " + id);
        //PreparedStatement preparedStmt = conn.prepareStatement
            return;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection conn=null;

        FlowerDAO obj = new FlowerDAO(null);

        Driver driver;

        String url;
        String username;
        String password;

        username = "root";
        password = "1234";
        url = "jdbc:mysql://localhost:3306/dbFlowerMarket?useSSL=false";

        try {
            driver = new FabricMySQLDriver();
        }catch (SQLException ex){
            System.out.println("Не удалось создать драйвер!");
            return;
        }

        try {
            DriverManager.registerDriver(driver);
        }catch (SQLException ex){
            System.out.println("Не удалось зарегистрировать драйвер");
            return;
        }

        System.out.println("Драйвер подключен");

        try {
            obj.conn = DriverManager.getConnection(url, username, password);

            obj.insertFlower("liliya","Buket", 100);

            for(int i =2;i<10;i++) {
              obj.deleteFlower(i);
            }

            obj.findListFlower("liliya");

            for( Flower flower : obj.getListFlower()) {
               System.out.println("Номер в выборке #" + flower.getId()
                       + "\t name = " + flower.getName()
                       + "\t category = " + flower.getCategory()
                       + "\t price = " + flower.getPrice());
            }
*/
/*
            Statement statement = obj.conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM flower");


            while (result.next()) {
            flower = new Flower(    result.getInt("id"),
                    result.getString("name"),
                    result.getString("category"),
                    result.getInt("price"));
            }

            // obj.deleteFlower(1);

            System.out.println("Номер в выборке #" + flower.getId()
                    + "\t name = " + flower.getName()
                    + "\t category = " + flower.getCategory()
                    + "\t price = " + flower.getPrice()
            );

        }catch (SQLException ex){
            System.out.println("Соединение не установлено!");
            return;
        }
        finally {
            if(conn!=null){
                conn.close();
            }
        }


        //================================================================================================

    }*/
}
