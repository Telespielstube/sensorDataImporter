package ohdm.storage;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import ohdm.bean.Sensor;

public interface TimestampInterface {

    /** Create the ohdm.sensor_timestamp table.
     * 
     * @throws SQLException     gets thrown if an error occurs creating table.
     */
    public void createTimestampTable() throws SQLException;
    
    /** Adds timestamp to the table.
     * 
     * @param parsedData            holds the timestamp data from the parsed cls file.
     * @param foreignKeySensorId    sensor id.
     * @throws SQLException         gets thrown if an error occurs while inserting data.
     */
    public long addTimestampData(Sensor parsedData) throws SQLException;
}