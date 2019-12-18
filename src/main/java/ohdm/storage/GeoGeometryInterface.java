package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.DataSource;
import ohdm.bean.Sensor;

public interface GeoGeometryInterface {
        
        /** Checks if data source already exists in table.
         */
       // public boolean checkIfIdExists(String systemName ) throws SQLException;
        
        /** adds the data source to the table.
         */
        public long addGeoGeometry(Sensor sensorData, long geoObjectId, long clazzId, long userId) throws SQLException;
}
