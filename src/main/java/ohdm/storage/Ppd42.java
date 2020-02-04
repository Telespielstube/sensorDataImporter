package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import ohdm.bean.Classification;
import ohdm.bean.Sensor;

public class Ppd42 extends SensorType {

    public Ppd42(ConnectionDb db) {
        super(db);
    }
    
    public void addPpdData(Sensor ppdData, Classification clazz, int typeId, long userId) throws SQLException, ParseException {
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
