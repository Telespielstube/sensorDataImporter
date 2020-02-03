package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.ExternalSystem;
import ohdm.bean.Sensor;

public interface GeoObjectGeometryInterface {
        
        /** Checks if data source already exists in table.
         */
       // public boolean checkIfIdExists(String systemName) throws SQLException;
        
        /** adds the data source to the table.
         */
        public long addGeoObjGeometry(Sensor sensorData, int pointId, int typeId, long geoObjectId, long clazzId, long userId) throws SQLException;
}
