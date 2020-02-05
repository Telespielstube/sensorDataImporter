package ohdm.storage.sensorType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ohdm.bean.Classification;
import ohdm.bean.Sensor;
import ohdm.storage.ConnectionDb;
import ohdm.storage.SensorType;
import ohdm.storage.SubClassName;

public class Ppd42 extends SensorType {
    
    public Ppd42(ConnectionDb db) {
        super(db);
    }
    
    public int convertTimestampToEpoch(String timestamp) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSz", Locale.GERMANY);
        Date date = simpleDateFormat.parse(timestamp);
        long epoch = date.getTime();
        return (int) (epoch / 1000);
    }
    
    public void addPpdData(Sensor ppdData, Classification clazz, int typeId, long userId) throws SQLException, ParseException {
        clazz.setSubClassificationName(SubClassName.finedust.toString()); // Sets subClassName to finedust
        long clazzId = super.addClassification(clazz);
        long geoObjectId = super.addGeoObject(ppdData, userId);
        long pointId = super.addPoint(ppdData, userId);
        super.addImportedSensor(ppdData, geoObjectId);
        super.addGeoObjGeometry(ppdData, pointId, typeId, geoObjectId, clazzId, userId);
        
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.fine_dust_data "
                + "(pm10, dur_pm10, ratio_pm10, pm25, dur_pm25, ratio_pm25, pm0, fine_dust_sensor_id_fkey) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setFloat(1, ppdData.getDataSample(0).getValue());
        statement.setFloat(2, ppdData.getDataSample(1).getValue());
        statement.setFloat(3, ppdData.getDataSample(2).getValue());
        statement.setFloat(4, ppdData.getDataSample(3).getValue());
        statement.setFloat(5, ppdData.getDataSample(4).getValue());
        statement.setFloat(6, ppdData.getDataSample(5).getValue());
        statement.setLong(7, userId);
        
        statement.executeUpdate();
    }
}
