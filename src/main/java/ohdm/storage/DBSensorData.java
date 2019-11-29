package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ohdm.sensorDataImporter.ParsedData;

public class DBSensorData {
	
	DBConnection db;
	
	public DBSensorData(DBConnection db) {
		this.db = db;
	}
	
	
	// Adds sensor type to sensor_type table.
	public int addNewSensorType(ParsedData sensorType) throws SQLException {
	    PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.sensor_data (type) VALUES("
	            + sensorType.getSensorId() + "'" + sensorType.getSensorType() +"')", Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet resultSet  = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt("id");       
    }
	
	// Adds temperature and humidity to temperature_data table.
	public int addNewSensorData(ParsedData tempData) throws SQLException {		
	    PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.sensor_data (temperature, humidity) VALUES("
				 + tempData.getValue1() + ","+ tempData.getValue2() +")", Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate();
		ResultSet resultSet  = statement.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt("id");  
	}

	//Adds fine dust data to fine_dust_data table.
	
}
