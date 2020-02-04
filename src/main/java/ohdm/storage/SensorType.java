package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.Classification;
import ohdm.bean.Sensor;

public class SensorType implements SensorInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;
    private int existingId = 0;

    public SensorType(ConnectionDb db) {
        this.db = db;
    }
    
    @Override
    public boolean checkIfIdExists(String entryName) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT * FROM ohdm.classification "
                + "WHERE subclassname = '" + entryName + "';");
        resultSet = statement.executeQuery();
        resultSet.next();    
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt(1 );
            return true;
        }
    }
    
    @Override
    public boolean checkIfIdExists(long entryId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT * FROM ohdm.imported_sensor "
                + "WHERE imported_id = " + entryId + ";");
        resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt(1);
            return true;
        }
    }
    @Override
    public boolean checkIfIdExists(Sensor sensorData) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT COUNT(point) "
                + "FROM ohdm.points WHERE point = ST_SetSRID( ST_Point(?, ?), 4326);");
        statement.setObject(1, );
        statement.setObject(2, sensorData.getLatitude());
        resultSet = statement.executeQuery();
        resultSet.next();    
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt("id");
            return true;
        }
    }
    
    @Override
    public long addClassification(Classification clazz) throws SQLException {
        long classId;
        if (!checkIfIdExists(clazz.getSubClassName())) {
            PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.classification "
                    + "(class, subclassname) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, clazz.getClassification());
            statement.setString(2, clazz.getSubClassName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            classId = resultSet.getInt("id");
        } else {
            classId = existingId;
        }
        return classId;
    }
    
    public long addGeoObject(Sensor sensorData, long userId) throws SQLException {
        long returnId;
        if (!checkIfIdExists(sensorData.getSensorType())) {
            PreparedStatement statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.geoobject (name, source_user_id) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, sensorData.getSensorType());
            statement.setLong(2, userId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            returnId = resultSet.getInt("id");
        } else {
            returnId = existingId;
        }
        return returnId;
    }
    
    @Override
    public long addPoint(Sensor sensorData, long foreignKeyId) throws SQLException {
        long returnId;
        if (!checkIfIdExists(sensorData)) {
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
            returnId = existingId;
        }
        return returnId;
    }
    
    public void addImportedSensor(Sensor sensorData, long geoObjectId) throws SQLException {
        if (!checkIfIdExists(sensorData.getImportedSensorId())) {
            PreparedStatement statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.imported_sensor (imported_id, geoobject_id) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sensorData.getImportedSensorId());
            statement.setLong(2, geoObjectId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
        }
    }
}
