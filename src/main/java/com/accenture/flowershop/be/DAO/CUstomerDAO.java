package com.accenture.flowershop.be.DAO;

import com.accenture.flowershop.be.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CUstomerDAO {

    // @SuppressWarnings("unused")
    private Connection conn;

    private List<User> listUser = new ArrayList<>();



    public List<User> getListUser() {
        return listUser;
    }
/*
    public void setListAccount(List<User> listUser) {
        this.listUser = listUser;
    }

//===========================================================================================================================

    public CUstomerDAO(Connection conn) {
        this.conn = conn;
    }

//===========================================================================================================================

    public List<User> findListCustomer(String firstName, String lastName)
            throws SQLException {
        try {
            Statement statement = null;
            statement = this.conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM User WHERE firstName = '" + firstName +"' AND lastName = '"+ lastName + "'");
            while (result.next()) {
                User customer = new User(	result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("address"),
                        result.getInt("account"),
                        result.getInt("tel")
                );
                this.getListUser().add(customer);
            }

        }catch (SQLException e1){
            System.out.println("Поиск не удался!");
        }
        return this.getListUser();
    }

//===========================================================================================================================

    public boolean insertCustomer(String firstName, String lastName, String address, int tel)
            throws SQLException {
        Statement statement = null;
        statement = this.conn.createStatement();
        statement.executeUpdate("Insert into User (firstName, lastName, address, account, tel) values ('" +firstName+ "','"+lastName+"','" +address+"','2000','"+tel+"')");

        ResultSet result = statement.executeQuery("SELECT * FROM User WHERE firstName = '" + firstName + "' AND lastName = '" + lastName+"'");

        if (result.next()) {
            System.out.println("Пользователь  | " + firstName + " " + lastName + " | добавлен...");
            return true;
        }
        return false;
    }

//===========================================================================================================================

    public void deleteCustomer(int id) throws SQLException {

        Statement statement = null;
        statement = this.conn.createStatement();
        statement.execute("DELETE FROM User WHERE id = " + id);
        return;
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        CUstomerDAO obj = new CUstomerDAO(null);

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

        System.out.println("Драйвер подключен...");

        try {
            obj.conn = DriverManager.getConnection(url, username, password);
            if(obj.conn!=null){
                System.out.println("Соединение установлено...");
            }
            obj.insertCustomer("Vasya","Ivanov", "g. Tver", 891345340);

         //   for(int i =2;i<10;i++) { obj.deleteCustomer(i); }

            obj.findListCustomer("Vasya", "Ivanov");

            for( User customer : obj.getListUser()) {
                System.out.println("Номер в выборке #" + customer.getId()
                        + "\t FirstName = " + customer.getFirstName()
                        + "\t LastName = " + customer.getLastName()
                        + "\t Address = " + customer.getAddress()
                        + "\t Account = " + customer.getAccount()
                        + "\t Tel = " + customer.getTel());
                }

        }catch (SQLException ex){
            System.out.println("!!!!!!!!Error!!!!!!!!!");
            return;
        }
        finally {
            if(obj.conn!=null){
                obj.conn.close();
            }
        }


        //================================================================================================

    }
    */
}
