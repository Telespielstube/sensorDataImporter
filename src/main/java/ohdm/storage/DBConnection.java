package ohdm.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public Connection connection;
	
	public DBConnection(String addr, String username, String pass) {
		try {//   		 
			connection = DriverManager.getConnection(addr, username, pass);
			
			
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
	}
	
	

}
