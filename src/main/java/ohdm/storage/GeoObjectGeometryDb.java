package ohdm.storage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ohdm.bean.Sensor;

public class GeoObjectGeometryDb implements GeoObjectGeometryInterface {
    private ConnectionDb db;
    private ResultSet resultSet = null;
    private long existingLocationId = 0;

    public GeoObjectGeometryDb(ConnectionDb db) {
        this.db = db;
    }

    public Date convertTimestampToDate(String timestamp) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) simpleDateFormat.parse(timestamp);
        return date;
    }

    public boolean checkIfGeometryLocationExists(long pointId) throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("SELECT * FROM ohdm.geoobject_geometry " + "WHERE id_target = '" + pointId + "';");
        resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingLocationId = resultSet.getInt(1);
            return true;
        }
    }

    public void addGeoObjGeometry(Sensor sensorData, long pointId, int typeId, long geoObjectId, long clazzId,
            long userId) throws SQLException, ParseException {
        PreparedStatement statement;
        Date parsedSampleDate = convertTimestampToDate(sensorData.getTimestamp());
        if (!checkIfGeometryLocationExists(pointId)) {
            statement = db.connection.prepareStatement("INSERT INTO ohdm.geoobject_geometry "
                    + "(id_target, type_target, id_geoobject_source, role, classification_id, valid_since, valid_until, source_user_id) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, pointId);
            statement.setInt(2, typeId);
            statement.setLong(3, geoObjectId);
            statement.setString(4, null);
            statement.setLong(5, clazzId);
            statement.setDate(6, parsedSampleDate); // valid_since
            statement.setDate(7, parsedSampleDate); // valid_until
            statement.setLong(8, userId);
        } else {
            statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.geoobject_geometry " + "(valid_until) " + "VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setDate(7, parsedSampleDate); // valid_until
        }
        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        resultSet.next();
    }
}
