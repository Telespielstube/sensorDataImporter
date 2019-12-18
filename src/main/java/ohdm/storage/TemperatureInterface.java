package ohdm.storage;

import java.sql.Date;
import java.sql.SQLException;

import ohdm.bean.Sensor;

public interface TemperatureInterface {
    
    /** Creates temperature table if it not exists.
     */
    public void createTemperatureTable() throws SQLException;
    
    /** Parses the date string to the postgis date with timezone format.
     * 
     * @param timestamp     timestamp of the measured sensor data.
     */
    public Date parseDate(String timestamp);
    
    /** Adds sensor data from dht sensor.
     * 
     * @param tempData              holds the parsed temperature/humidity data of the dht sensor. 
     * @param foreignKeySensorId    sensor id foreign key which connects table to sensor type table.
     * @throws SQLException         throws exception if some SQL query error occurs.
     */
    public void addDhtData(Sensor tempData, int foreignKeySensorId) throws SQLException;
}
