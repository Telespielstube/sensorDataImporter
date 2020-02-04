package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.postgis.*;

import ohdm.bean.Sensor;

public class PointsDb implements PointsInterface {
    private ConnectionDb db;
    private ResultSet resultSet = null;
    private int existingClazzId = 0;

    public PointsDb(ConnectionDb db) {
        this.db = db;
    }
    
    @Override
    public boolean checkIfPointsIdExists(Sensor sensorData) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT COUNT(point) "
                + "FROM ohdm.points WHERE point = ST_SetSRID( ST_Point(?, ?), 4326);");
        statement.setObject(1, sensorData.getLongitude());
        statement.setObject(2, sensorData.getLatitude());
        resultSet = statement.executeQuery();
        resultSet.next();    
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingClazzId = resultSet.getInt("id");
            return true;
        }
    }

    @Override
    public long addPoint(Sensor sensorData, long foreignKeyId) throws SQLException {
        long returnId;
        if (!checkIfPointsIdExists(sensorData)) {
            PreparedStatement statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.points (point, source_user_id) VALUES(2, ST_SetSRID(ST_MakePoint(?, ?), 4326)", 
                    Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, sensorData.getLongitude());
            statement.setObject(2, sensorData.getLatitude());
            statement.setLong(3, foreignKeyId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            returnId = resultSet.getInt("id");
        } else {
            returnId = existingClazzId;
        }
        return returnId;
  
    }
}