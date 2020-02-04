package ohdm.storage;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IdVerificationInterface {
    
    /** Checks the ohdm.classification table if the subclassname already exists.
     * 
     * @param subclassname      the sublcassname to check for.
     * @return                  the new inserted id or the already existing id of the subclassname.
     */
    public boolean checkIfClassificationIdExists(String subClasssName) throws SQLException;

    /**
     * 
     * @param name
     * @return
     * @throws SQLException
     */
    public boolean checkIfIdExists(String name) throws SQLException;
    
    /** Checks the ohdm.points table if the id already exists.
     * 
     * @param subclassname      the sublcassname to check for.
     * @return                  the new inserted id or the already existing id of the subclassname.
     */
    public boolean checkIfPointsIdExists(int location) throws SQLException;
    
    /** Checks if id is already inserted into table.
     * 
     * @param importedSensorId      imported sensor id from the parsed .csv file.
     * @return                      returns false if id is not already inserted or true if id is already in. 
     * @throws SQLException         throws exception if some SQL query error occurs.
     */
    public boolean checkIfIdExists(ResultSet resultSet, int importedSensorId) throws SQLException;
    
    /** Checks the table if geometry location already exists.
     * 
     * @param pointId           Location of the sensor
     * @return                  true if location exists and false if not.
     * @throws SQLException     is thrown if a sql query error occurs.
     */
    public boolean checkIfGeometryLocationExists(long pointId) throws SQLException;
}
