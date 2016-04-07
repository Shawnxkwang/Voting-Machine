/**
 * Created by Xiaokai Wang.
 * Do Not Execute this file Again!!!!
 * Tables are already created into the db!!!!
 */
//STEP 1. Import required packages
import java.sql.*;
import com.mysql.jdbc.Driver;
public class InitDB {
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
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql2;
            String sql1;
            System.out.println("Creating Tables...");
            sql1 = "CREATE TABLE Posters" +
                    "(id INTEGER NOT NULL, " +
                    "content VARCHAR(100)," +
                    "PRIMARY KEY(id))";
            sql2 = "CREATE TABLE Vote" +
                    "(email VARCHAR(100) NOT NULL, " +
                    "id INTEGER, " +
                    "PRIMARY KEY(email), " +
                    "FOREIGN KEY (id) REFERENCES Posters(id))";
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            System.out.println("Created table in given database...");

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
}//end FirstExample
