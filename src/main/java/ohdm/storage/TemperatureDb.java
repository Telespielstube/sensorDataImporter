package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ohdm.bean.Sensor;

public class TemperatureDb implements TemperatureInterface {

    private ConnectionDb db;
  //  private ResultSet resultSet = null;

    public TemperatureDb(ConnectionDb db) {
        this.db = db;
    }

    public int convertTimestampToEpoch(String timestamp) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
        Date date = simpleDateFormat.parse(timestamp);
        long epoch = date.getTime();
        return (int)(epoch/1000);
    }
    
    public void createTemperatureTable() throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("CREATE TABLE IF NOT EXISTS ohdm.temperature_data\n" + 
                        "(\n" + 
                        "    id bigint NOT NULL DEFAULT nextval('ohdm.temperature_data_temperature_id_seq'::regclass),\n" + 
                        "    temperature real,\n" + 
                        "    humidity real,\n" + 
                        "    sensor_id bigint,\n" + 
                        "    timestamp_id bigint NOT NULL,\n" + 
                        "    CONSTRAINT temperature_data_pkey PRIMARY KEY (id),\n" + 
                        "    CONSTRAINT temperature_data_timestamp_id_fkey FOREIGN KEY (timestamp_id)\n" + 
                        "        REFERENCES ohdm.sensor_timestamp (id) MATCH SIMPLE\n" + 
                        "        ON UPDATE NO ACTION\n" + 
                        "        ON DELETE NO ACTION\n" + 
                        ")");
        statement.executeUpdate();
        statement.close();
    }

    public void addDhtData(Sensor dhtData, long foreignKeyId) throws SQLException, ParseException {
        int unixTime = convertTimestampToEpoch(dhtData.getDataSample(0).getTimestamp());
        PreparedStatement statement = db.connection.prepareStatement(
                "INSERT INTO ohdm.temperature_data (temperature, humidity, timestamp_id, geoobject_id) VALUES(?, ?, ?, ?)");
        statement.setFloat(1, dhtData.getDataSample(1).getValue());
        statement.setFloat(2, dhtData.getDataSample(2).getValue());
        statement.setLong(3, unixTime);
        statement.setLong(4, foreignKeyId);
        statement.executeUpdate();
    }
}
