package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.sensorDataImporter.ParsedData;

public class SensorTypeDb {

    private DBConnection db;
    private ResultSet resultSet = null;

    public SensorTypeDb(DBConnection db) {
        this.db = db;
    }
    
    //checks if id is already inserted into table.
    public boolean checkIfIdIsInDatabase(ResultSet resultSet, int importedSensorId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement(
                "SELECT imported_id FROM ohdm.sensor_type WHERE imported_id = " + importedSensorId + ";");
        statement.executeQuery();
        if (resultSet.getFetchSize() > 0)
            return true;
        else
            return false;
    }
    
    // Adds sensor type to sensor_type table.
    public int addSensorType(ParsedData parsedData) throws SQLException {       
   //     if (checkIfIdIsInDatabase(resultSet, parsedData.getImportedSensorId())) {
            System.out.println("INSERT INTO ohdm.sensor_type (imported_id, sensor_type) VALUES(" + parsedData.getImportedSensorId() + "'" + parsedData.getSensorType() + "')");
            // PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.sensor_type (imported_id, type) VALUES("+ parsedData.getImportedSensorId() + "'" + parsedData.getSensorType() + "')", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.sensor_type (imported_id, sensor_type) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parsedData.getImportedSensorId());
            statement.setString(2, parsedData.getSensorType());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
     //   }
        return resultSet.getInt("sensor_id");
    }
}
