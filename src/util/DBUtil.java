/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author jdleg
 */
public class DBUtil {
    private static final String JBDC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    
    private static Connection connection = null;
    
    private static final String connStr = "jdbc:oracle:thin:@oracle12c.scs.ryerson.ca:1521:orcl12c";
    
    //connect to db
    public static void dbConnect() throws SQLException, ClassNotFoundException{
       try{
           Class.forName(JBDC_DRIVER);
       }catch(ClassNotFoundException e){
           System.out.println("Where is your JBDC driver");
           e.printStackTrace();
           throw e;
       }
       System.out.println("JBDC Driver has been registered");
       
       try {
           connection = DriverManager.getConnection(connStr, "jcrewe", "06215121");
       } catch(SQLException e){
           System.out.println("Connection failed");
           throw e;
       }
       System.out.println("Connection Succeeded");
    }
    
    //disconnect from db
    public static void dbDisconnect() throws SQLException{
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch(Exception e){
            throw e;
        }
    }
    
    //this is for insert/delete/update operations
    public static void dbExecuteQuery(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        try {
            dbConnect();
            System.out.println("Connected");
            stmt = connection.createStatement();
            System.out.println("Statement Created");
            stmt.executeUpdate(sqlStmt);
            System.out.println("Update Executed");
        } catch(SQLException e) {
            System.out.println("Problem occurred at dbExecuteQuery " + e);
            throw e;
        } finally {
            if(stmt!= null) {
                stmt.close();
            }
            dbDisconnect();
        }

    }
    
    
    
    //used for retrieving records
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        
        try{
            dbConnect();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sqlQuery);
            crs = new CachedRowSetImpl();
            crs.populate(rs);
        } catch(SQLException e) {
            System.out.println("Error in dbExecute operation " + e);
            throw e;
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }
    
    //for creating and dropping tables
    public static String[] dbExecuteMultipleUpdate(String sqlStmt) throws SQLException, ClassNotFoundException{
        Statement stmt = null;
        String[] sqls = sqlStmt.split(";");
        try {
            dbConnect();
            System.out.println("Connected");
            stmt = connection.createStatement();
            System.out.println("Statement Created");
            for (String sql : sqls) {
                System.out.println(sql);
                stmt.execute(sql);
            }
            System.out.println("Update Executed");
        } catch(SQLException e) {
            System.out.println("Problem occurred at dbExecuteMultipleUpdate " + e);
            throw e;
        } finally {
            if(stmt!= null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return sqls;
    }
    
}
