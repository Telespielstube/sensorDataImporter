package ohdm.storage;

import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public interface TemperatureInterface {
    
    /** Creates temperature table if it not exists.
     */
    public void createTemperatureTable();
    
    /** Adds sensor data from dht sensor.
     * 
     * @param tempData              holds the parsed temperature/humidity data of the dht sensor. 
     * @param foreignKeySensorId    sensor id foreign key which connects table to sensor type table.
     * @throws SQLException         throws exception if some SQL query error occurs.
     */
    public void addDhtData(ParsedData tempData, int foreignKeySensorId) throws SQLException;
}
