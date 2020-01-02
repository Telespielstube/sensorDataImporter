package ohdm.storage;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import ohdm.bean.Sensor;

public interface TimestampInterface {

    /** Create the ohdm.sensor_timestamp table.
     * 
     * @throws SQLException     gets thrown if an error occurs creating table.
     */
    public void createTimestampTable() throws SQLException;
    
    /** Parses the timestamp string from the csv file to the postgresql conform date format. 
     * 
     * @param timestamp     date and time as string.
     * @return              timestamp string as sql conform Date format.
     */
    public Timestamp parseDate(String timestamp);
    
    /** Adds timestamp to the table.
     * 
     * @param parsedData            holds the timestamp data from the parsed cls file.
     * @param foreignKeySensorId    sensor id.
     * @throws SQLException         gets thrown if an error occurs while inserting data.
     */
    public long addTimestampData(Sensor parsedData, long foreignKeySensorId) throws SQLException;
}