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

public class Ppd42 extends SensorType {

    public Ppd42(ConnectionDb db) {
        super(db);
    }

    public LocalDateTime convertTimestampToDate(String timestamp) throws ParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(timestamp.substring(0, 19).replace('T', ' '), dateFormatter);
        return date;
    }

    public void addPpdData(Sensor ppdData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = convertTimestampToDate(ppdData.getTimestamp());
        
        clazz.setSubClassificationName(SubClassName.finedust.toString()); // Sets subClassName to finedust
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(ppdData, userId);
        long pointId = super.addPoint(ppdData, userId);
        super.addImportedSensor(ppdData, geoObjectId);
        super.addGeoObjGeometry(ppdData, pointId, typeId, geoObjectId, clazzId, userId);

        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.fine_dust_data "
                + "(pm10, dur_pm10, ratio_pm10, pm25, dur_pm25, ratio_pm25, timestamp, geoobject_id) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setFloat(1, ppdData.getDataSample(0).getValue());
        statement.setFloat(2, ppdData.getDataSample(1).getValue());
        statement.setFloat(3, ppdData.getDataSample(2).getValue());
        statement.setFloat(4, ppdData.getDataSample(3).getValue());
        statement.setFloat(5, ppdData.getDataSample(4).getValue());
        statement.setFloat(6, ppdData.getDataSample(5).getValue());
        statement.setObject(7, date);
        statement.setLong(8, userId);

        statement.executeUpdate();
    }
}
