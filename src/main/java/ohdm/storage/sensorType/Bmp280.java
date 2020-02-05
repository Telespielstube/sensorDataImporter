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

public class Bmp280 extends SensorType {

    public Bmp280(ConnectionDb db) {
        super(db);
    }

    public LocalDateTime convertTimestampToDate(String timestamp) throws ParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(timestamp.substring(0, 19).replace('T', ' '), dateFormatter);
        return date;
    }
    
    public void addBmpData(Sensor bmpData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = convertTimestampToDate(bmpData.getTimestamp());
   
        clazz.setSubClassificationName(SubClassName.airpressure.toString()); // Sets subclassName to temperature
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(bmpData, userId);
        long pointId = super.addPoint(bmpData, userId);
        super.addImportedSensor(bmpData, geoObjectId);
        super.addGeoObjGeometry(bmpData, pointId, typeId, geoObjectId, clazzId, userId);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.air_pressure_data "
                + "(pressure, humidity, timestamp, geoobject_id) VALUES(?, ?, ?, ?)");
        statement.setFloat(1, bmpData.getDataSample(0).getValue());
        statement.setFloat(2, bmpData.getDataSample(1).getValue());
        statement.setObject(3, date);
        statement.setLong(4, userId);
        
        statement.executeUpdate();
    }
}
