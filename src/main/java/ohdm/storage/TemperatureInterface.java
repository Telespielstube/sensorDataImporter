package ohdm.storage;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

import ohdm.bean.Sensor;

public interface TemperatureInterface {
    
    /** Converts the string timestamp from the .csv file to the unix time fomrat.
     * 
     * @param timestamp         .csv file timestamp as string.
     * @return                  timestamp as unix time.
     * @throws ParseException   Is thrown if a parsing error occurs.
     */
    public int convertTimestampToEpoch(String timestamp) throws ParseException;
    
    /** Creates temperature table if it not exists.
     */
    public void createTemperatureTable() throws SQLException;
    
    /** Adds sensor data from dht sensor.
     * 
     * @param tempData              holds the parsed temperature/humidity data of the dht sensor. 
     * @param foreignKeySensorId    sensor id foreign key which connects table to sensor type table.
     * @throws SQLException         throws exception if some SQL query error occurs.
     * @throws ParseException       Throws exception if a parsing error occurs.
     */
    public void addDhtData(Sensor tempData, long foreignKeyId) throws SQLException, ParseException;
}
