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

public class Dht22 extends SensorType {
    
    public Dht22(ConnectionDb db) {
        super(db);
    }

    public void addDhtData(Sensor dhtData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = super.convertStringToTimestamp(dhtData.getTimestamp());
   
        clazz.setSubClassificationName(SubClassName.temperature.toString()); // Sets subclassName to temperature
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(dhtData, userId);
        long pointId = super.addPoint(dhtData, userId);
        super.addImportedSensor(dhtData, geoObjectId);
        super.addGeoObjGeometry(dhtData, pointId, typeId, geoObjectId, clazzId, userId);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.temperature_data "
                + "(temperature, humidity, timestamp, geoobject_id) VALUES(?, ?, ?, ?)");
        statement.setFloat(1, dhtData.getDataSample(0).getValue());
        statement.setFloat(2, dhtData.getDataSample(1).getValue());
        statement.setObject(3, date);
        statement.setLong(4, geoObjectId);
        
        statement.executeUpdate();
    }
}
