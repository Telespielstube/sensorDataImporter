package ohdm.storage;

import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.bean.Sensor;

public interface ImportedSensorInterface {
    
    /** Creates imported_sensor table if it not exists already.
     */
    public void createImportedSensorTable() throws SQLException;
    
    /** Checks if id is already inserted into table.
     * 
     * @param importedSensorId      imported sensor id from the parsed .csv file.
     * @return                      returns false if id is not already inserted or true if id is already in. 
     * @throws SQLException         throws exception if some SQL query error occurs.
     */
    public boolean checkIfIdExists(ResultSet resultSet, int importedSensorId) throws SQLException;
    
    /** Connects the imported sensor id from the .csv file to the geo_object id.
     * 
     * @param sensorData        parsed essential sensor type data from the .csv file. 
     * @param geoObjectId       geoObject Id which is conntected to the source user id.
     * @return                  sensor type primary key or already inserted primary key. 
     * @throws SQLException     throws exception if some SQL query error occurs.
     */
    public void addImportedSensor(Sensor sensorData, long geoObjectId) throws SQLException;
}
