package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ohdm.bean.Sensor;

public interface GeoObjectInterface {
    
    /**
     * 
     * @param name
     * @return
     * @throws SQLException
     */
    public boolean checkIfIdExists(String name) throws SQLException;

    /**
     * 
     * @param sensorData
     * @return
     * @throws SQLException
     */
    public long addGeoObject(Sensor sensorData, long userId) throws SQLException;
}
