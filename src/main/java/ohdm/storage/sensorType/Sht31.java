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

public class Sht31 extends SensorType {;
    
    public Sht31(ConnectionDb db) {
        super(db);
    }

    public void addShtData(Sensor shtData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = super.convertStringToTimestamp(shtData.getTimestamp());
   
        clazz.setSubClassificationName(SubClassName.temperature.toString()); // Sets subclassName to temperature
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(shtData, userId);
        long pointId = super.addPoint(shtData, userId);
        super.addImportedSensor(shtData, geoObjectId);
        super.addGeoObjGeometry(shtData, pointId, typeId, geoObjectId, clazzId, userId);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.temperature_data "
                + "(temperature, humidity, timestamp, geoobject_id) VALUES(?, ?, ?, ?)");
        statement.setFloat(1, shtData.getDataSample(0).getValue());
        statement.setFloat(2, shtData.getDataSample(1).getValue());
        statement.setObject(3, date);
        statement.setLong(4, geoObjectId);
        
        statement.executeUpdate();
    }
}
