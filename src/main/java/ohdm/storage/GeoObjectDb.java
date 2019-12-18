package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.Sensor;

public class GeoObjectDb implements GeoObjectInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;
    private long existingId;

    public GeoObjectDb(ConnectionDb db) {
        this.db = db;
    }

    public boolean checkIfIdExists(String sensorType) throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("SELECT COUNT(name) FROM ohdm.geoobject WHERE name = " + sensorType + ";");
        resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt(1);
            return true;
        }
    }

    public long addGeoObject(Sensor sensorData, long foreignKeyId) throws SQLException {
        long returnId;
        if (!checkIfIdExists(sensorData.getSensorType())) {
            PreparedStatement statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.geoobject (name, source_user_id) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, sensorData.getSensorType());
            statement.setLong(2, foreignKeyId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            returnId = resultSet.getInt("id");
        } else {
            returnId = existingId;
        }
        return returnId;
    }
}
