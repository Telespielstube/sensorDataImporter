package ohdm.storage;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import ohdm.bean.Classification;
import ohdm.bean.Sensor;

public interface SensorInterface {
    
    /** Checks the table if the string already exists.
     * 
     * @param subclassname      the sublcassname to check for.
     * @return                  the new inserted id or the already existing id of the subclassname.
     */
    public boolean checkIfIdExists(String entryName) throws SQLException;
    
    /** Checks the table if the sensor object already exists.
     * 
     * @param subclassname      the sublcassname to check for.
     * @return                  the new inserted id or the already existing id of the subclassname.
     */
    public boolean checkIfIdExists(Sensor sensorData) throws SQLException;
    
    /** Checks if id is already inserted into table.
     * 
     * @param importedSensorId      imported sensor id from the parsed .csv file.
     * @return                      returns false if id is not already inserted or true if id is already in. 
     * @throws SQLException         throws exception if some SQL query error occurs.
     */
    public boolean checkIfIdExists(long entryId) throws SQLException;
    
    
    
    
    
    /** Adds the sensor classifications to the ohdm.classification table.
     * 
     * @param classification    topic which classifies the object. in this context its sensor.
     * @param subclassname      classifies the sensor type. e.g. fine dust sensor.
     * @return                  classification id.
     */
    public long addClassification(Classification clazz) throws SQLException;
    
    /**
     * 
     * @param sensorData
     * @return
     * @throws SQLException
     */
    public long addGeoObject(Sensor sensorData, long userId) throws SQLException;
    
    /** Adds the latitude and longitude angles to the ohdm.points table to mark the location of the sensor.
     * 
     * @param sensorData        Sensor object.
     * @param foreignKeyId      source_user_id connects the point to the user id.
     * @return                  points id.
     */
    public long addPoint(Sensor sensorData, long foreignKeyId) throws SQLException;
    
    /** Connects the imported sensor id from the .csv file to the geo_object id.
     * 
     * @param sensorData        parsed essential sensor type data from the .csv file. 
     * @param geoObjectId       geoObject Id which is conntected to the source user id.
     * @return                  sensor type primary key or already inserted primary key. 
     * @throws SQLException     throws exception if some SQL query error occurs.
     */
    public void addImportedSensor(Sensor sensorData, long geoObjectId) throws SQLException;
    
    /** Parses the string timestamp to Date format yyyy-MM-dd.
     * 
     * @param timestamp         timestamp as string
     * @return                  parsed date.
     * @throws ParseException   is thrown if a parsing error occurs.
     */
    public LocalDateTime convertTimestampToDate(String timestamp) throws ParseException;
    
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
