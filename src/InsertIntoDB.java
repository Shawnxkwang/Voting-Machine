
/**
 * Created by Xiaokai Wang.
 * Insert sample data into tables.
 * DO NOT CHANGE ANYTHING.
 */


//STEP 1. Import required packages
import java.sql.*;
import java.util.Random;

import com.mysql.jdbc.Driver;
public class InsertIntoDB {
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
            // get user email
            //String email = getEmail();
            //String pid = getPid();
            //STEP 4: Execute a query
            System.out.println("Inserting...");
            stmt = conn.createStatement();

            // Auto Generate Samples
            String sql;
            for (int j = 1; j <= 10; j++){
                int id = j;
                String n = "aa"+j;
                String d = "bb"+j;
                int c = j*j;
                sql = "INSERT INTO GroupTable VALUES('"+id+"', '"+n+"', '"+n+"', '"+c+"')";
                stmt.executeUpdate(sql);
            }

            String email;String fn;String ln;
            String sql2;

            for (int i = 0; i < 101; i++){
                email = i+"xiw@pitt.edu";
                fn = "james"+i;
                ln = "john"+i;

                String insert = "INSERT INTO UserTable VALUES(email,firstName,lastName,birthday)" +
                        "VALUES (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(insert);
                ps.setString(1,email);
                ps.setString(2,fn);
                ps.setString(3,ln);
                ps.setString(4,"19860416");
                ps.executeUpdate();

            }
          System.out.print("----------------\n");

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

