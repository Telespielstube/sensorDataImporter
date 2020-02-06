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

public class Sds011 extends SensorType {
    public Sds011(ConnectionDb db) {
        super(db);
    }


    public void addSdsData(Sensor sdsData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = super.convertStringToTimestamp(sdsData.getTimestamp());
        clazz.setSubClassificationName(SubClassName.finedust.toString()); // Sets subClassName to finedust 
        
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(sdsData, userId);
        long pointId = super.addPoint(sdsData, userId);
        super.addImportedSensor(sdsData, geoObjectId);
        super.addGeoObjGeometry(sdsData, pointId, typeId, geoObjectId, clazzId, userId);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.fine_dust_data "
                + "(pm10, pm25, timestamp, geoobject_id) "
                + "VALUES(?, ?, ?, ?)");
        statement.setFloat(1, sdsData.getDataSample(0).getValue());
        statement.setFloat(2, sdsData.getDataSample(1).getValue());
        statement.setObject(3, date);
        statement.setLong(4, geoObjectId);

        statement.executeUpdate();
    }
}
