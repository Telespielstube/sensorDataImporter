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

public class Pms extends SensorType {
    public Pms(ConnectionDb db) {
        super(db);
    }

    public void addPmsData(Sensor pmsData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = super.convertStringToTimestamp(pmsData.getTimestamp());
        clazz.setSubClassificationName(SubClassName.finedust.toString()); // Sets subClassName to finedust 
        
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(pmsData, userId);
        long pointId = super.addPoint(pmsData, userId);
        super.addImportedSensor(pmsData, geoObjectId);
        super.addGeoObjGeometry(pmsData, pointId, typeId, geoObjectId, clazzId, userId);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.fine_dust_data "
                + "(pm10, pm25, pm0, timestamp, geoobject_id) "
                + "VALUES(?, ?, ?, ?, ?)");
        statement.setFloat(1, pmsData.getDataSample(0).getValue());
        statement.setFloat(2, pmsData.getDataSample(1).getValue());
        statement.setFloat(3, pmsData.getDataSample(2).getValue());
        statement.setObject(4, date);
        statement.setLong(5, geoObjectId);

        statement.executeUpdate();
    }
}
