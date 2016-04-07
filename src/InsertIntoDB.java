
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
            Random rand = new Random();
            String[] contents = {"Data Minning", "Machine Learning", "Game Dev", "WiCS",
                    "Pitt CS", "Intership", "Job Opportunity", "Random"};
            for (int i = 1; i <= 500; i++){

                // auto gen contents
                String content = contents[rand.nextInt(8)];
                sql = "INSERT INTO Posters VALUES('"+i+"','"+content+"')";
                stmt.executeUpdate(sql);
            }

            //STEP 5: Clean-up environment
            System.out.println("Data Inserted!");
            System.out.println("Poster Data:");
            String sql2;
            sql2 = "SELECT id, content FROM Posters ORDER BY id";
            ResultSet rs = stmt.executeQuery(sql2);

            //STEP 5: Extract data from result set

            System.out.println("-------------------------");
            System.out.println("Poster ID " +" | "+" Content ");
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                String c = rs.getString("content");
                // Print Data.
                System.out.println("  "+id+ "        |   " +c+ " ");

            }
            System.out.println("-------------------------");
            rs.close();
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

