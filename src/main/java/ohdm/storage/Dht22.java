package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ohdm.bean.Sensor;

public class Dht22 extends SensorType {
    
    public Dht22(ConnectionDb db) {
        super(db);
    }

    public int convertTimestampToEpoch(String timestamp) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
        Date date = simpleDateFormat.parse(timestamp);
        long epoch = date.getTime();
        return (int)(epoch/1000);
    }
    
    public void addDhtData(Sensor dhtData, Classification clazz, long userId) throws SQLException, ParseException {
        int unixTime = convertTimestampToEpoch(dhtData.getTimestamp());
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(dhtData.get(i), userId);
        long pointId = super.addPoint(sensorDataList.get(i), userId);
        super.addImportedSensor(sensorDataList.get(i), geoObjectId);
        super.addGeoObjGeometry(sensorDataList.get(i), pointId, typeId, geoObjectId, clazzId, userId);
        PreparedStatement statement = db.connection.prepareStatement(
                "INSERT INTO ohdm.temperature_data (temperature, humidity, timestamp_id, geoobject_id) VALUES(?, ?, ?, ?)");
        statement.setFloat(1, dhtData.getDataSample(1).getValue());
        statement.setFloat(2, dhtData.getDataSample(2).getValue());
        statement.setLong(3, unixTime);
        statement.setLong(4, foreignKeyId);
        statement.executeUpdate();
    }
}
