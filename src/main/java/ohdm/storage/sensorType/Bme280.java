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

public class Bme280 extends SensorType {

    public Bme280(ConnectionDb db) {
        super(db);
    }

    public LocalDateTime convertTimestampToDate(String timestamp) throws ParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(timestamp.substring(0, 19).replace('T', ' '), dateFormatter);
        return date;
    }
    
    public void addBmeData(Sensor bmeData, Classification clazz, int typeId, long userId)
            throws SQLException, ParseException {
        LocalDateTime date = convertTimestampToDate(bmeData.getTimestamp());
   
        clazz.setSubClassificationName(SubClassName.airpressure.toString()); // Sets subclassName to temperature
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(bmeData, userId);
        long pointId = super.addPoint(bmeData, userId);
        super.addImportedSensor(bmeData, geoObjectId);
        super.addGeoObjGeometry(bmeData, pointId, typeId, geoObjectId, clazzId, userId);
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.air_pressure_data "
                + "(pressure, temperature, , humidity, timestamp, geoobject_id) VALUES(?, ?, ?, ?, ?)");
        statement.setFloat(1, bmeData.getDataSample(0).getValue());
        statement.setFloat(2, bmeData.getDataSample(1).getValue());
        statement.setFloat(3, bmeData.getDataSample(2).getValue());
        statement.setObject(4, date);
        statement.setLong(5, userId);
        
        statement.executeUpdate();
    }
}
