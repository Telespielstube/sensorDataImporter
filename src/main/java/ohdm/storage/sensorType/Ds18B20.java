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

public class Ds18B20 extends SensorType {
    
    public Ds18B20(ConnectionDb db) {
        super(db);
    }

    public void addDs18Data(Sensor ds18Data, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = super.convertStringToTimestamp(ds18Data.getTimestamp());
   
        clazz.setSubClassificationName(SubClassName.temperature.toString()); // Sets subclassName to temperature
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(ds18Data, userId);
        long pointId = super.addPoint(ds18Data, userId);
        super.addImportedSensor(ds18Data, geoObjectId);
        super.addGeoObjGeometry(ds18Data, pointId, typeId, geoObjectId, clazzId, userId);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.temperature_data "
                + "(temperature, timestamp, geoobject_id) VALUES(?, ?, ?)");
        statement.setFloat(1, ds18Data.getDataSample(0).getValue());
        statement.setObject(2, date);
        statement.setLong(3, geoObjectId);
        
        statement.executeUpdate();
    }
}
