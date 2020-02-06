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

public class Htu21 extends SensorType {
    
    public Htu21(ConnectionDb db) {
        super(db);
    }

    public void addHtuData(Sensor htuData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = super.convertStringToTimestamp(htuData.getTimestamp());
   
        clazz.setSubClassificationName(SubClassName.temperature.toString()); // Sets subclassName to temperature
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(htuData, userId);
        long pointId = super.addPoint(htuData, userId);
        super.addImportedSensor(htuData, geoObjectId);
        super.addGeoObjGeometry(htuData, pointId, typeId, geoObjectId, clazzId, userId);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.temperature_data "
                + "(temperature, humidity, timestamp, geoobject_id) VALUES(?, ?, ?, ?)");
        statement.setFloat(1, htuData.getDataSample(0).getValue());
        statement.setFloat(2, htuData.getDataSample(1).getValue());
        statement.setObject(3, date);
        statement.setLong(4, geoObjectId);
        
        statement.executeUpdate();
    }
}
