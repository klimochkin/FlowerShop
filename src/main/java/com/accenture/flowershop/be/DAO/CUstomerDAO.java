package com.accenture.flowershop.be.DAO;

import com.accenture.flowershop.be.entity.user.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CUstomerDAO {

    // @SuppressWarnings("unused")
    private Connection conn;

    private List<Customer> listCustomer = new ArrayList<>();


    public List<Customer> getListCustomer() {
        return listCustomer;
    }
/*
    public void setListAccount(List<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }

//===========================================================================================================================

    public CUstomerDAO(Connection conn) {
        this.conn = conn;
    }

//===========================================================================================================================

    public List<Customer> findListCustomer(String firstName, String lastName)
            throws SQLException {
        try {
            Statement statement = null;
            statement = this.conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM Customer WHERE firstName = '" + firstName +"' AND lastName = '"+ lastName + "'");
            while (result.next()) {
                Customer customer = new Customer(	result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("address"),
                        result.getInt("account"),
                        result.getInt("tel")
                );
                this.getListCustomer().add(customer);
            }

        }catch (SQLException e1){
            System.out.println("Поиск не удался!");
        }
        return this.getListCustomer();
    }

//===========================================================================================================================

    public boolean insertCustomer(String firstName, String lastName, String address, int tel)
            throws SQLException {
        Statement statement = null;
        statement = this.conn.createStatement();
        statement.executeUpdate("Insert into Customer (firstName, lastName, address, account, tel) values ('" +firstName+ "','"+lastName+"','" +address+"','2000','"+tel+"')");

        ResultSet result = statement.executeQuery("SELECT * FROM Customer WHERE firstName = '" + firstName + "' AND lastName = '" + lastName+"'");

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
        statement.execute("DELETE FROM Customer WHERE id = " + id);
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

            for( Customer customer : obj.getListCustomer()) {
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
