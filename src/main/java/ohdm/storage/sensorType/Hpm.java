package ohdm.storage.sensorType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import ohdm.bean.Classification;
import ohdm.bean.Sensor;
import ohdm.bean.SubClassName;
import ohdm.storage.ConnectionDb;
import ohdm.storage.SensorType;

public class Hpm extends SensorType {
    public Hpm(ConnectionDb db) {
        super(db);
    }

    public LocalDateTime convertTimestampToDate(String timestamp) throws ParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(timestamp.substring(0, 19).replace('T', ' '), dateFormatter);
        return date;
    }

    public void addhpmData(Sensor hpmData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = convertTimestampToDate(hpmData.getTimestamp());
        clazz.setSubClassificationName(SubClassName.finedust.toString()); // Sets subClassName to finedust 
        
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(hpmData, userId);
        long pointId = super.addPoint(hpmData, userId);
        super.addImportedSensor(hpmData, geoObjectId);
        super.addGeoObjGeometry(hpmData, pointId, typeId, geoObjectId, clazzId, userId);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.fine_dust_data "
                + "(pm10, pm25, timestamp, geoobject_id) "
                + "VALUES(?, ?, ?, ?)");
        statement.setFloat(1, hpmData.getDataSample(0).getValue());
        statement.setFloat(2, hpmData.getDataSample(1).getValue());
        statement.setObject(3, date);
        statement.setLong(4, userId);

        statement.executeUpdate();
    }
}
