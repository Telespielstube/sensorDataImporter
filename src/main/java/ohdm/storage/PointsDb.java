package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.Object;
import org.postgresql.geometric.*;

import ohdm.bean.Sensor;

public class PointsDb implements PointsInterface {
    private ConnectionDb db;
    private ResultSet resultSet = null;
    private int existingClazzId = 0;

    public PointsDb(ConnectionDb db) {
        this.db = db;
    }
    
    @Override
    public boolean checkIfPointsIdExists(int location) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT COUNT(point) "
                + "FROM ohdm.points WHERE point = " + location + ";");
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
        if (!checkIfPointsIdExists(sensorData.getLocation())) {
            PreparedStatement statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.points " + "(point, source_user_id) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, sensorData.getLocation());
            statement.setLong(2, foreignKeyId);
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