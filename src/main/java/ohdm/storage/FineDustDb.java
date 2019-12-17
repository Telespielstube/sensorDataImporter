package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public class FineDustDb implements FineDustInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;

    public FineDustDb(ConnectionDb db) {
        this.db = db;
    }

    public void createFineDustTable() throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ohdm.fine_dust_data\n"
                + "(\n"
                + "    fine_dust_id bigint NOT NULL DEFAULT nextval('ohdm.fine_dust_data_fine_dust_id_seq'::regclass),\n"
                + "    pm10 real,\n" + "    dur_pm10 real,\n" + "    ratio_pm10 real,\n" + "    pm25 real,\n"
                + "    dur_pm25 real,\n" + "    ratio_pm25 real,\n" + "    pm0 real,\n"
                + "    fine_dust_sensor_id_fkey bigint,\n"
                + "    CONSTRAINT fine_dust_data_pkey PRIMARY KEY (fine_dust_id),\n"
                + "    CONSTRAINT fine_dust_data_fine_dust_sensor_id_fkey_fkey FOREIGN KEY (fine_dust_sensor_id_fkey)\n"
                + "        REFERENCES ohdm.sensor_type (sensor_id) MATCH SIMPLE\n" + "        ON UPDATE NO ACTION\n"
                + "        ON DELETE NO ACTION\n" + "        NOT VALID)");
        statement.executeUpdate();
        statement.close();
    }

    public void addPpdData(ParsedData dustData, int foreignKeySensorId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.fine_dust_data "
                + "(pm10, dur_pm10, ratio_pm10, pm25, dur_pm25, ratio_pm25, pm0, fine_dust_sensor_id_fkey) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setFloat(1, dustData.getValue1());
        statement.setFloat(2, dustData.getValue2());
        statement.setFloat(3, dustData.getValue3());
        statement.setFloat(4, dustData.getValue4());
        statement.setFloat(5, dustData.getValue5());
        statement.setFloat(6, dustData.getValue6());
        statement.setInt(7, foreignKeySensorId);
        statement.executeUpdate();
        // resultSet.next();
    }
}
