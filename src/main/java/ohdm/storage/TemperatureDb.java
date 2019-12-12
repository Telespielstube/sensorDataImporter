package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public class TemperatureDb implements TemperatureInterface {
    
    private DBConnection db;
    private ResultSet resultSet = null;

    public TemperatureDb(DBConnection db) {
        this.db = db;
    }
    
    // Adds temperature and humidity to temperature_data table.
    public void addDhtData(ParsedData tempData, int foreignKeySensorId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.temperature_data (temperature, humidity, temperature_sensor_id_fkey) VALUES(?, ?, ?)");
        statement.setFloat(1, tempData.getValue1());
        statement.setFloat(2, tempData.getValue2());
        statement.setInt(3,  foreignKeySensorId);
        statement.executeUpdate();
      //  resultSet.next();
    }

}
