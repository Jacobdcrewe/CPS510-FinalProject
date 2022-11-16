/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookings.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;

/**
 *
 * @author jdleg
 */
public class bookingsDAO {
    //insert into command to create a customer
    public static void insertBooking(String departure, String destination, String transport) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Bookings(departure, destination, mode_of_transport) " + "VALUES('"+departure+"','"+destination+"','"+transport+"')";
        
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while inserting data " +e);
            e.printStackTrace();
            throw e;
        }
        
    }
    
    
    //updates destination based on service number
    public static void updateDest(int serNo, String destination) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE BOOKINGS SET destination = '"+destination+"' where service_no = '"+serNo+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while updating destination " +e);
            e.printStackTrace();
            throw e;
        }
        
        
    }
    
    //updates departure based on serviceNumber
    public static void updateDep(int serNo, String departure) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE BOOKINGS SET departure = '"+departure+"' where service_no = '"+serNo+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while updating departure " +e);
            e.printStackTrace();
            throw e;
        }
        
        
    }
    
    //updates teh transport based on service number
    public static void updateTrans(int serNo, String transport) throws ClassNotFoundException, SQLException{
        String sql = "UPDATE BOOKINGS SET mode_of_transport = '"+transport+"' where service_no = '"+serNo+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while updating transport " +e);
            e.printStackTrace();
            throw e;
        }
        
        
    }
    
    //deletes the booking based on the service no
    public static void deleteBooking(int serNo) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Bookings WHERE service_no ='"+serNo+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while deleting a booking " +e);
            e.printStackTrace();
            throw e;
        }
    }
    
    //returns all records 
    public static ObservableList<Bookings> getAllRecords() throws ClassNotFoundException, SQLException {
        String sql = "select * from bookings";
        try{
            System.out.println(sql);
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Bookings> bookList = getBookingObjects(rsSet);
            return bookList;
        } catch(SQLException e) {
            System.out.println("Exception occur while getting all records " +e);
            e.printStackTrace();
            throw e;
        }
    }

    //funciton to create a list of all the customers and then return the list
    private static ObservableList<Bookings> getBookingObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Bookings> bookList = FXCollections.observableArrayList();
            while(rsSet.next()) {
                Bookings book = new Bookings();
                book.setServiceNo(rsSet.getInt("service_no"));
                book.setDep(rsSet.getString("departure"));
                book.setDest(rsSet.getString("destination"));
                book.setTrans(rsSet.getString("mode_of_transport"));
                book.setReturnNo(rsSet.getInt("return_no"));
                bookList.add(book);
            }
            return bookList;
        } catch(SQLException e) {
            System.out.println("Error occured while getting the data from db " +e);
            e.printStackTrace();
            throw e;
        }
        
    }
    
    //funciton to find specific customer based on id
    public static ObservableList<Bookings> searchBookings(String serNo) throws ClassNotFoundException, SQLException {
        String sql = "select * from bookings where service_no =" + serNo;
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Bookings> list = getBookingObjects(rsSet);
            return list;
        } catch(SQLException e) {
            System.out.println("Error occured while searching the records " + e);
            e.printStackTrace();
            throw e;
        }
    } 

    public static ObservableList<Bookings> getReturnNo(int serNo) throws ClassNotFoundException, SQLException {
        String sql = "select * from bookings where service_no =" + getNum(serNo);
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Bookings> list = getBookingObjects(rsSet);
            return list;
        } catch(SQLException e) {
            System.out.println("Error occured while searching the records " + e);
            e.printStackTrace();
            throw e;
        }
    }
    
    private static int getNum(int serNo) throws ClassNotFoundException, SQLException {
        String sql = "select * from bookings where service_no =" + serNo;
        try {
            ResultSet rsSet = DBUtil.dbExecute(sql);
            ObservableList<Bookings> list = getBookingObjects(rsSet);
            System.out.println(list.get(0).getReturnNo());
            return list.get(0).getReturnNo();
            
        } catch(SQLException e) {
            System.out.println("Error occured while searching the records " + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void updateReturnNo(int serNo, int returnNo) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE BOOKINGS SET return_no = '"+returnNo+"' where service_no = '"+serNo+"'";
        try{
            System.out.println(sql);
            DBUtil.dbExecuteQuery(sql);
        } catch(SQLException e) {
            System.out.println("Exception occur while updating destination " +e);
            e.printStackTrace();
            throw e;
        }
    }
    
}
