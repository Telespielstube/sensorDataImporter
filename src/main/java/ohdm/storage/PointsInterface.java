package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.Sensor;

public interface PointsInterface {
    /** Checks the ohdm.points table if the id already exists.
     * 
     * @param subclassname      the sublcassname to check for.
     * @return                  the new inserted id or the already existing id of the subclassname.
     */
    public boolean checkIfPointsIdExists(Sensor sensorData) throws SQLException;
    
    /** Adds the latitude and longitude angles to the ohdm.points table to mark the location of the sensor.
     * 
     * @param sensorData        Sensor object.
     * @param foreignKeyId      source_user_id connects the point to the user id.
     * @return                  points id.
     */
    public long addPoint(Sensor sensorData, long foreignKeyId) throws SQLException;
}

