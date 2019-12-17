package ohdm.storage;

import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public interface FineDustInterface {  
    
    /** Creates fine dust table if it not exists already.
     */
    public void createFineDustTable() throws SQLException;
    
    /** Adds sensor data from the ppd sensor.
     * 
     * @param dustData              holds the parsed fionedust data of the ppd sensor.
     * @param foreignKeySensorId    sensor id foreign key which connects table to sensor type table. 
     * @throws SQLException         throws exception if some SQL query error occurs.
     */
    public void addPpdData(ParsedData dustData, int foreignKeySensorId) throws SQLException;
}
