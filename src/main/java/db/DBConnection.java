package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection conn;
    private static DBConnection instance;

    DBConnection() throws SQLException {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_management_system", "root", "1234");
    }

    public  Connection getConnection(){
        return conn;
    }
    public static DBConnection getInstance() throws SQLException {
        if(instance==null){
            instance = new DBConnection();
        }
        return instance;
    }


}
