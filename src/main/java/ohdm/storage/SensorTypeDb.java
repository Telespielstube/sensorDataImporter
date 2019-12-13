package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.sensorDataImporter.ParsedData;

public class SensorTypeDb {

    private DBConnection db;
    private ResultSet resultSet = null;
    private int previousSensorId;
    
    public SensorTypeDb(DBConnection db) {
        this.db = db;
    }

    //checks if id is already inserted into table.
    public boolean checkIfIdIsInDatabase(int importedSensorId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT * FROM ohdm.sensor_type WHERE imported_id = " + importedSensorId + ";");
        resultSet = statement.executeQuery();
        resultSet.next();    
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            previousSensorId = resultSet.getInt(1);
            return true;
        }
    }
    
    // Adds sensor type to sensor_type table.
    public int addSensorType(ParsedData parsedData) throws SQLException {       
        int returnId;
        if (!checkIfIdIsInDatabase(parsedData.getImportedSensorId())) {
            System.out.println("INSERT INTO ohdm.sensor_type (imported_id, sensor_type) VALUES(" + parsedData.getImportedSensorId() + "'" + parsedData.getSensorType() + "')");
            PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.sensor_type (imported_id, sensor_type) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parsedData.getImportedSensorId());
            statement.setString(2, parsedData.getSensorType());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            returnId = resultSet.getInt("sensor_id");
        } else {
            returnId = previousSensorId;
        }
        return returnId;
        
    }
}