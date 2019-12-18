package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.DataSource;
import ohdm.bean.Sensor;

public class GeoGeometryDb implements GeoGeometryInterface {
    private ConnectionDb db;
    private ResultSet resultSet = null;
    private long existingId;

    public GeoGeometryDb(ConnectionDb db) {
        this.db = db;
    }

    public long addGeoGeometry(Sensor sensorData, long geoObjectId, long clazzId, long userId) throws SQLException {
        long returnId;
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.geoobject_geometry "
                + "(id_geoobject_source, classification_id, valid_since, valid_until, source_user_id) "
                + "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        statement.setLong(4, geoObjectId);
        statement.setLong(6, clazzId);
        statement.setFloat(8, sensorData.getLatitude());
        statement.setFloat(9, sensorData.getLongitude());
        statement.setLong(12, userId);
        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        resultSet.next();
        returnId = resultSet.getInt("id");
        return returnId;
    }

}
