package ohdm.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public Connection connection;
	
	public DBConnection(String addr, String username, String pass) {
		try {  		 
			connection = DriverManager.getConnection(addr, username, pass);	
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
	}
}
