package ohdm.storage;

import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.bean.Sensor;

public interface IdVerificationInterface {
    
    /** Checks the ohdm.classification table if the subclassname already exists.
     * 
     * @param subclassname      the sublcassname to check for.
     * @return                  the new inserted id or the already existing id of the subclassname.
     */
    public boolean checkIfIdExists(String entryName) throws SQLException;
    
    /** Checks the ohdm.points table if the id already exists.
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
    
}
