/**
 * Created by Xiaokai Wang.
 * Clean up DB. DO NOT CHANGE ANYTHING.
 */


//STEP 1. Import required packages
import java.sql.*;
import java.util.Random;

import com.mysql.jdbc.Driver;
public class CleanUpDB {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://sql5.freesqldatabase.com/sql5114097";

    //  Database credentials
    static final String USER = "sql5114097";
    static final String PASS = "dVgZpI9vLn";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Cleaning...");
            stmt = conn.createStatement();

            // Auto Generate Samples
            //String sql;
            //String sql1;
            String sql2;
            String sql3;
            String sql7;
            String sql8;String sql9;

            // remove data

            //sql1 = "DELETE FROM Vote";
            //stmt.executeUpdate(sql1);
            //sql = "DELETE FROM Posters";
            //stmt.executeUpdate(sql);

            // remove tables

           sql2 = "DROP TABLE Friendship";
           stmt.executeUpdate(sql2);

            sql3 = "DROP TABLE Message";
          stmt.executeUpdate(sql3);
            sql7 = "DROP TABLE Membership";
            stmt.executeUpdate(sql7);

            sql8 = "DROP TABLE GroupTable";
            stmt.executeUpdate(sql8);

            sql9 = "DROP TABLE UserTable";
            stmt.executeUpdate(sql9);



            //STEP 5: Clean-up environment

            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end

