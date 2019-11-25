package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.SensorData;

public class DBSensorData {
	
	DBConnection db;
	
	public DBSensorData(DBConnection db) {
		this.db = db;
	}
	
	public int addNewSensorData(SensorData sensordata) throws SQLException {		
		PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.sensor_data ( type, timestamp, temperature, humidity) VALUES("
																	
																	+"'"+sensordata.getSensorType()
																	+"','"+sensordata.getTimestamp()
				 													+"',"+sensordata.getTemperature()
				 													+","+sensordata.getHumidity()
				 													+")", Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate();
		ResultSet resultSet  = statement.getGeneratedKeys();
		resultSet.next();
		return resultSet.getInt("id");
       
	}

}
