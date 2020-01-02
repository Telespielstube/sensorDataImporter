package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.bean.Sensor;

public class TemperatureDb implements TemperatureInterface {

    private ConnectionDb db;
  //  private ResultSet resultSet = null;

    public TemperatureDb(ConnectionDb db) {
        this.db = db;
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

    public void addDhtData(Sensor tempData, long foreignKeyId, long timestampId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement(
                "INSERT INTO ohdm.temperature_data (temperature, humidity, sensor_id, timestamp_id) VALUES(?, ?, ?, ?)");
        statement.setFloat(1, tempData.getDataSample(0).getValue());
        statement.setFloat(2, tempData.getDataSample(1).getValue());
        statement.setLong(3, foreignKeyId);
        statement.setLong(4, timestampId);
        statement.executeUpdate();
    }
}
