package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by DM on 04.10.2016.
 */
public class DatabaseOperations {
    public Connection dbConnection = null;

    public void connectDB(){

        String strUrl = "jdbc:derby:wagons";
        try {
            dbConnection = DriverManager.getConnection(strUrl);
            System.out.println("Connection OK!");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void closeDB() {
        if (dbConnection != null)
            try {
                dbConnection.close();
                System.out.println("Connection closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
