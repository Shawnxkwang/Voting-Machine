/**
 * Created by Xiaokai Wang.
 * Do Not Execute this file Again!!!!
 * Tables are already created into the db!!!!
 * DO NOT CHANGE ANYTHING.
 */
//STEP 1. Import required packages
import java.sql.*;
import com.mysql.jdbc.Driver;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
            String sql3;
            String sql4;
            String sql5;
            System.out.println("Creating Tables...");
            sql1 = "create table UserTable (" +

                    "email varchar(128) not null," +
                    "firstName varchar(64) not null," +
                    "lastName varchar(64) not null," +
                    "birthday varchar(64)," +

                    "primary key(email)" +
                    ")";
            sql2 = "create table Friendship (" +
                    "person1 varchar(128) not null," +
                    "person2 varchar(128) not null," +
                    "timeInitiated timestamp not null," +
                    "timeEstablished timestamp," +
                    "primary key(person1, person2)," +
                    "foreign key(person1) references UserTable(email)," +
                    "foreign key(person2) references UserTable(email)" +
                    ")";
            sql3 = "create table GroupTable (" +
                    "groupID integer not null," +
                    "name varchar(64) not null," +
                    "description varchar(1024)," +
                    "mLimit integer not null," +
                    "primary key(groupID)" +
                    ")";
            sql4 = "create table Membership (" +
                    "groupID integer not null," +
                    "member varchar(707) not null," +
                    "primary key(groupID, member)," +
                    "foreign key(groupID) references GroupTable(groupID)," +
                    "foreign key(member) references UserTable(email)" +
                    ")";
            sql5 = "create table Message (" +
                    "msgID integer not null," +
                    "senderEmail varchar(128) not null," +
                    "recipientEmail varchar(128) not null," +
                    "time_sent timestamp," +
                    "msg_subject varchar(700)," +
                    "msg_body varchar(700)," +
                    "primary key(msgID)," +
                    "foreign key(senderEmail) references UserTable(email)," +
                    "foreign key(recipientEmail) references UserTable(email)" +
                    ")";
            String sql6 = "SELECT version()";
            ResultSet rs = stmt.executeQuery(sql6);

            while(rs.next()){


                String email = rs.getString("VERSION()");
                System.out.println(email);


            }
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            stmt.executeUpdate(sql4);
            stmt.executeUpdate(sql5);
            stmt.executeQuery(sql6);
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
}//end
