/**
 * Created by Xiaokai Wang.
 * Queries are provided.
 */


//STEP 1. Import required packages
import java.sql.*;
import java.util.Random;

import com.mysql.jdbc.Driver;
public class GetInfoToDB {
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
            System.out.println("Selecting...");
            stmt = conn.createStatement();
            // get user email
            //String email = getEmail();
            //String pid = getPid();

            String sql1;

            String[] emails = {"www@pitt.edu", "222@pitt.edu", "333@gmail.com", "444@gmail.com"};
            for (int o = 0; o < 4; o++){
                int idd = o+1;
                String email = emails[o];
                sql1 = "INSERT INTO Vote VALUES('"+email+"', '"+idd+"')";
                stmt.executeUpdate(sql1);
            }
            String sql;
            sql = "INSERT INTO Vote VALUES('123@qq.com','2')";
            stmt.executeUpdate(sql);
            //sql1 = "INSERT INTO Vote VALUES('"+email+"', '"+pid+"')";
            //stmt.executeUpdate(sql1);



            String sql2;
            sql2 = "SELECT Vote.id as new, Posters.content as con, COUNT(Vote.id) AS c " +
                    "FROM Vote LEFT JOIN Posters ON Vote.id = Posters.id "+
                    "GROUP BY new "+
                    "ORDER BY c DESC "
                    ;
            ResultSet rs = stmt.executeQuery(sql2);

            //STEP 5: Extract data from result set
            System.out.println("Now the results are: " );
            System.out.println("-------------------------");
            System.out.println("Poster ID " +" | "+" Total  " + " | " + " Content ");
            while(rs.next()){
                //Retrieve by column name
                String id  = rs.getString("new");
                int total = rs.getInt("c");
                String con = rs.getString("con");
                // Print Data.
                System.out.println("  "+ id + "        |   " + total + "      |  " + con + " " );

            }
            System.out.println("-------------------------");



            //STEP 6: Clean-up environment
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
}//end FirstExample

