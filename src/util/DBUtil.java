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
    //proiding a driver for the connection to the database
    private static final String JBDC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    
    //forming a connection
    private static Connection connection = null;
    
    //giving a path to the database (in this case to ryerson server)
    private static final String connStr = "jdbc:oracle:thin:@oracle12c.scs.ryerson.ca:1521:orcl12c";
    
    //connect to db
    public static void dbConnect() throws SQLException, ClassNotFoundException{
       try{
           //creating a class
           Class.forName(JBDC_DRIVER);
       }catch(ClassNotFoundException e){
           System.out.println("Where is your JBDC driver");
           e.printStackTrace();
           throw e;
       }
       System.out.println("JBDC Driver has been registered");
       
       try {
           //connecting to the link and signing in to the database
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
            //if the connection exists and it isnt closed close the connection
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
            //connect to the database
            dbConnect();
            System.out.println("Connected");
            //open a statement for the dbs
            stmt = connection.createStatement();
            System.out.println("Statement Created");
            //executing the passed sqlstmt
            stmt.executeUpdate(sqlStmt);
            System.out.println("Update Executed");
        } catch(SQLException e) {
            System.out.println("Problem occurred at dbExecuteQuery " + e);
            throw e;
        } finally {
            //if the statement has value close the statement creation
            if(stmt!= null) {
                stmt.close();
            }
            //once everything has run disconnect from the dbs
            dbDisconnect();
        }

    }
    
    
    
    //used for retrieving records
    public static ResultSet dbExecute(String sqlQuery) throws ClassNotFoundException, SQLException {
        //creating variables
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        
        try{
            //connecting to dbs
            dbConnect();
            //creating a statment
            stmt = connection.createStatement();
            //running the passed value as a query to the dbs
            rs = stmt.executeQuery(sqlQuery);
            crs = new CachedRowSetImpl();
            //puttig the returned dbs values into a CachedRowSetImpl array
            crs.populate(rs);
        } catch(SQLException e) {
            System.out.println("Error in dbExecute operation " + e);
            throw e;
        } finally {
            //closing both result set and statements
            if(rs != null) {
                rs.close();
            }
            if(stmt != null) {
                stmt.close();
            }
            //disconnect from dbs
            dbDisconnect();
        }
        return crs;
    }
    
    //for creating and dropping tables
    public static String[] dbExecuteMultipleUpdate(String sqlStmt) throws SQLException, ClassNotFoundException{
        //creating statements
        Statement stmt = null;
        //taking the passed string value of multiple statements and making it into an array that is split at the end of each statement
        String[] sqls = sqlStmt.split(";");
        try {
            //connect to dbs
            dbConnect();
            System.out.println("Connected");
            //creating a statment
            stmt = connection.createStatement();
            System.out.println("Statement Created");
            //executing a statement for each item in the array
            for (String sql : sqls) {
                System.out.println(sql);
                stmt.execute(sql);
            }
            System.out.println("Update Executed");
        } catch(SQLException e) {
            System.out.println("Problem occurred at dbExecuteMultipleUpdate " + e);
            throw e;
        } finally {
            //closing the stmts and disconnecting.
            if(stmt!= null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return sqls;
    }
    
}
