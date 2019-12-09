package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ohdm.sensorDataImporter.ParsedData;

public class DBSensorData {
	
	DBConnection db;
	
	public DBSensorData(DBConnection db) {
		this.db = db;
	}
	
	// checks if id is already inserted into table.
	public boolean checkIfIdIsInDatabase(ResultSet resultSet, int importedSensorId) throws SQLException {
	    PreparedStatement statement = db.connection.prepareStatement("SELECT imported_id FROM ohdm.sensor_type WHERE imported_id = "+importedSensorId+";");
	    statement.executeQuery();
	    if (resultSet.getFetchSize() > 0)
	    	return true;
	    else 
	    	return false;
	}

	// Adds sensor type to sensor_type table. 
	public int addNewSensorType(ParsedData parsedData) throws SQLException {
	    ResultSet resultSet = null;
	    if (checkIfIdIsInDatabase(resultSet, parsedData.getImportedSensorId())) {
	    	System.out.println("INSERT INTO ohdm.sensor_type (imported_id, type) VALUES("
	                + parsedData.getImportedSensorId() + "'" + parsedData.getSensorType() +"' + )");
	        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.sensor_type (imported_id, type) VALUES("
	                + parsedData.getImportedSensorId() + "'" + parsedData.getSensorType() +"' + )", Statement.RETURN_GENERATED_KEYS);
	        statement.executeUpdate();
	        resultSet  = statement.getGeneratedKeys();
	        resultSet.next();
	    }
	    return resultSet.getInt("sensor_id");
	}
	
	// Adds temperature and humidity to temperature_data table.
	public int addNewSensorData(ParsedData tempData, int foreignKeySensorId) throws SQLException {		
	    PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.sensor_data (temperature, humidity, sensor_id) VALUES("
				 + tempData.getValue1() + ","+ tempData.getValue2() + "," + foreignKeySensorId + ")", Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate();
		ResultSet resultSet  = statement.getGeneratedKeys();
		//resultSet.next();
		return resultSet.getInt("temperature_id");  
	}

	//Adds fine dust data to fine_dust_data table.
	
}
