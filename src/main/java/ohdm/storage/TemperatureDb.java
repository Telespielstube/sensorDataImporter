package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public class TemperatureDb implements TemperatureInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;

    public TemperatureDb(ConnectionDb db) {
        this.db = db;
    }

    public void createTemperatureTable() throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("CREATE TABLE IF NOT EXISTS ohdm.temperature_data\n" + "(\n"
                        + "    temperature_id bigint NOT NULL DEFAULT nextval('ohdm.temperature_data_temperature_id_seq'::regclass),\n"
                        + "    temperature real,\n" + "    humidity real,\n"
                        + "    temperature_sensor_id_fkey bigint,\n"
                        + "    CONSTRAINT temperature_data_pkey PRIMARY KEY (temperature_id),\n"
                        + "    CONSTRAINT temperature_data_temperature_sensor_id_fkey_fkey FOREIGN KEY (sensor_id)\n"
                        + "    REFERENCES ohdm.sensor_type (sensor_id) MATCH SIMPLE\n" + "    ON UPDATE NO ACTION\n"
                        + "    ON DELETE NO ACTION\n" + "    NOT VALID)");
        statement.executeUpdate();
        statement.close();
    }

    public void addDhtData(ParsedData tempData, int foreignKeySensorId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement(
                "INSERT INTO ohdm.temperature_data (temperature, humidity, sensor_id) VALUES(?, ?, ?)");
        statement.setFloat(1, tempData.getValue1());
        statement.setFloat(2, tempData.getValue2());
        statement.setInt(3, foreignKeySensorId);
        statement.executeUpdate();
        // resultSet.next();
    }

}
