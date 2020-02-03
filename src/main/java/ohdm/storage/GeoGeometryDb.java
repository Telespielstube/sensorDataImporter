package ohdm.storage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.Sensor;

public class GeoGeometryDb implements GeoGeometryInterface {
    private ConnectionDb db;
    private ResultSet resultSet = null;
    private long existingId;

    public GeoGeometryDb(ConnectionDb db) {
        this.db = db;
    }

    
    public long addGeoGeometry(Sensor sensorData, long geoObjectId, long clazzId, long userId) throws SQLException {
        long geometryId;
        Date today = new Date(System.currentTimeMillis());

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.geoobject_geometry "
                + "(id_target, type_target, id_geoobject_source, role, classification_id, valid_since, valid_until, source_user_id) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setLong(1, 0);
        statement.setInt(2,  0);
        statement.setLong(3, geoObjectId);
        statement.setString(4, null);
        statement.setLong(5, clazzId);
        statement.setDate(6, today); // valid_since
        statement.setDate(7, today); // valid_until
        statement.setLong(8, userId); 
        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        resultSet.next();
        geometryId = resultSet.getInt(1);
        return geometryId;
    }

}
