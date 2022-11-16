/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customers.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;

/**
 *
 * @author jdleg
 */
public class customersDAO {
    
    //insert into command to create a customer
    public static void insertCustomer(String username, String password, String cardNo) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO CUSTOMERS(username, userPass, cardNo) " + "VALUES('"+username+"','"+password+"','"+cardNo+"')";
        
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while inserting data " +e);
            e.printStackTrace();
            throw e;
        }
        
    }
    
    
    //updates password based on id
    public static void updatePassword(int id, String password) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE CUSTOMERS SET userpass = '"+password+"' where customerid = '"+id+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while updating password " +e);
            e.printStackTrace();
            throw e;
        }
        
        
    }
    
    //updates teh card number based on id
    public static void updateCard(int id, String card) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE CUSTOMERS SET cardNo = '"+card+"' where customerid = '"+id+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while updating card number " +e);
            e.printStackTrace();
            throw e;
        }
        
        
    }
    
    //deletes the customers based on the customer id
    public static void deleteCustomer(int id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE customerID ='"+id+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while deleting a customer " +e);
            e.printStackTrace();
            throw e;
        }
    }
    
    //returns all records 
    public static ObservableList<Customers> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from customers";
        try{
            System.out.println(sql);
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Customers> custList = getCustomerObjects(rsSet);
            return custList;
        } catch(SQLException e) {
            System.out.println("Exception occur while getting all records " +e);
            e.printStackTrace();
            throw e;
        }
    }

    //funciton to create a list of all the customers and then return the list
    private static ObservableList<Customers> getCustomerObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Customers> custList = FXCollections.observableArrayList();
            while(rsSet.next()) {
                Customers cust = new Customers();
                cust.setCustomerId(rsSet.getInt("customerID"));
                cust.setUsername(rsSet.getString("username"));
                cust.setPassword(rsSet.getString("userpass"));
                cust.setCardNo(rsSet.getString("cardNo"));
                cust.setPoints(rsSet.getInt("loyaltypoints"));
                custList.add(cust);
            }
            return custList;
        } catch(SQLException e) {
            System.out.println("Error occured while getting the data from db " +e);
            e.printStackTrace();
            throw e;
        }
        
    }
    
    //funciton to find specific customer based on id
    public static ObservableList<Customers> searchCustomers(String custId) throws ClassNotFoundException, SQLException {
        String sql = "select * from customers where customerid =" + custId;
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Customers> list = getCustomerObjects(rsSet);
            return list;
        } catch(SQLException e) {
            System.out.println("Error occured while searching the records " + e);
            e.printStackTrace();
            throw e;
        }
    } 

    public static void updatePoints(int id, int points) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE CUSTOMERS SET loyaltyPoints = '"+points+"' where customerid = '"+id+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while updating card number " +e);
            e.printStackTrace();
            throw e;
        }
    }
}
