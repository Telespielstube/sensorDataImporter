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

    public void createSensorTypeTable() {
        try {
            PreparedStatement statement = db.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ohdm.sensor_type(\n" + 
                    "    sensor_id bigint NOT NULL DEFAULT nextval('ohdm.sensor_type_sensor_id_seq'::regclass),\n" + 
                    "    imported_id bigint,\n" + 
                    "    sensor_type text COLLATE pg_catalog.\"default\",\n" + 
                    "    CONSTRAINT sensor_type_pkey PRIMARY KEY (sensor_id))");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Some error occured while creating sensor_type table. Check the stacktrace below.");
            e.printStackTrace();
        }
        
    }
    
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