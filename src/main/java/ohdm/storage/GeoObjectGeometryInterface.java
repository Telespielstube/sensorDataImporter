package ohdm.storage;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import ohdm.bean.ExternalSystem;
import ohdm.bean.Sensor;

public interface GeoObjectGeometryInterface {
    
    /** Parses the string timestamp to Date format yyyy-MM-dd.
     * 
     * @param timestamp         timestamp as string
     * @return                  parsed date.
     * @throws ParseException   is thrown if a parsing error occurs.
     */
    public Date convertTimestampToDate(String timestamp) throws ParseException;
    
    /** Checks the table if geometry location already exists.
     * 
     * @param pointId           Location of the sensor
     * @return                  true if location exists and false if not.
     * @throws SQLException     is thrown if a sql query error occurs.
     */
    public boolean checkIfGeometryLocationExists(long pointId) throws SQLException;
        
    /** Adds da
     * 
     * @param sensorData  Sensor object.
     * @param pointId     Primary key from points table where the location of the
     *                    sensor is stored.
     * @param typeId
     * @param geoObjectId Primary key from geo object table
     * @param clazzId
     * @param userId
     * @return
     * @throws SQLException
     * @throws ParseException 
     */
    public void addGeoObjGeometry(Sensor sensorData, long pointId, int typeId, long geoObjectId, long clazzId, long userId) throws SQLException, ParseException;
}
