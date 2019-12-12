package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public class FineDustDb implements FineDustInterface {

    private DBConnection db;
    private ResultSet resultSet = null;

    public FineDustDb(DBConnection db) {
        this.db = db;
    }
    
    // Adds fine dust data to fine_dust_data table.
    public void addPpdData(ParsedData dustData, int foreignKeySensorId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.fine_dust_data (pm10, dur_pm10, ratio_pm10, pm25, dur_pm25, ratio_pm25, pm0, fine_dust_sensor_id_fkey) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setFloat(1, dustData.getValue1());
        statement.setFloat(2, dustData.getValue2());
        statement.setFloat(3, dustData.getValue3());
        statement.setFloat(4, dustData.getValue4());
        statement.setFloat(5, dustData.getValue5());
        statement.setFloat(6, dustData.getValue6());
        statement.setInt(7,  foreignKeySensorId);
        statement.executeUpdate();
     //   resultSet.next();
    }
}
