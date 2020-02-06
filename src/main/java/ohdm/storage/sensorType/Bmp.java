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

public class Bmp extends SensorType {

    public Bmp(ConnectionDb db) {
        super(db);
    }
    
    public void addBmpData(Sensor bmpData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = super.convertStringToTimestamp(bmpData.getTimestamp());
   
        clazz.setSubClassificationName(SubClassName.airpressure.toString()); // Sets subclassName to temperature
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(bmpData, userId);
        long pointId = super.addPoint(bmpData, userId);
        super.addImportedSensor(bmpData, geoObjectId);
        super.addGeoObjGeometry(bmpData, pointId, typeId, geoObjectId, clazzId, userId);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.air_pressure_data "
                + "(pressure, temperature, timestamp, geoobject_id) VALUES(?, ?, ?, ?)");
        statement.setFloat(1, bmpData.getDataSample(0).getValue());
        statement.setFloat(2, bmpData.getDataSample(1).getValue());
        statement.setObject(3, date);
        statement.setLong(4, geoObjectId);
        
        statement.executeUpdate();
    }
}
