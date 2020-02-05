package ohdm.storage;

import java.sql.SQLException;

public interface TableInterface {

    /**
     * Creates table for fine dust data if it not exists.
     * 
     * @throws SQLException     is thrown if a sql error occurs.
     */
    public void createFineDustTable() throws SQLException;
    
    /**
     * Creates table for temperature data if it not exists.
     * 
     * @throws SQLException     is thrown if a sql error occurs.
     */
    public void createTemperatureTable() throws SQLException;
    
    /**
     *  Creates table for air pressure data if not exists.
     *  
     * @throws SQLException     is thrown if a sql error occurs.
     */ 
    public void createAirPressureTable() throws SQLException;
    
    /**
     * Creates table for imported sensor ids if it not exists.
     * 
     * @throws SQLException     is thrown if a sql error occurs.
     */
    public void createImportedSensorTable() throws SQLException;
}
