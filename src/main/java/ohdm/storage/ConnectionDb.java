package ohdm.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
	
	public Connection connection;
	
	/** Connection constructor which connects application to the ohdm postgis database.
	 *  
	 * @param addr         (server) address where the database is located.
	 * @param username     identifies the user. 
	 * @param pass         password to authenticate the user. 
	 */
	public ConnectionDb(String addr, String username, String pass) {
		try {  		 
			connection = DriverManager.getConnection(addr, username, pass);	
        } catch (SQLException e) {
            System.err.println("Connection failure.");
            e.printStackTrace();
        }
	}
}