package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.Sensor;

public interface PointsInterface {
    /** Checks the ohdm.classification table if the subclassname already exists.
     * 
     * @param subclassname      the sublcassname to check for.
     * @return                  the new inserted id or the already existing id of the subclassname.
     */
    public boolean checkIfPointsIdExists(int location) throws SQLException;
    
    /** Adds the sensor classifications to the ohdm.classification table.
     * 
     * @param classification    topic which classifies the object. in this context its sensor.
     * @param subclassname      classifies the sensor type. e.g. fine dust sensor.
     * @return                  classification id.
     */
    public long addPoint(Sensor sensorData, long foreignKeyId) throws SQLException;
}

