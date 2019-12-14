package ohdm.storage;

import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public interface SensorInterface {
    
    /** Creates sensor_type table if it not exists already.
     */
    public void createSensorTypeTable();
    
    /** Checks if id is already inserted into table.
     * 
     * @param importedSensorId      imported sensor id from the parsed .csv file.
     * @return                      returns false if id is not already inserted or true if id is already in. 
     * @throws SQLException         throws exception if some SQL query error occurs.
     */
    public boolean checkIfIdIsInDatabase(ResultSet resultSet, int importedSensorId) throws SQLException;
    
    /** Adds the sensor type to the sensor table.
     * 
     * @param parsedData        parsed essential sensor type data from the .csv file. 
     * @return                  sensor type primary key or already inserted primary key. 
     * @throws SQLException     throws exception if some SQL query error occurs.
     */
    public int addSensorType(ParsedData parsedData) throws SQLException;
}
