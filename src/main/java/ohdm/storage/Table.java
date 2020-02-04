package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Table implements TableInterface {

    private ConnectionDb db;

    public Table(ConnectionDb db) {
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

    public void createTemperatureTable() throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("CREATE TABLE IF NOT EXISTS ohdm.temperature_data\n" + "(\n"
                        + "    id bigint NOT NULL DEFAULT nextval('ohdm.temperature_data_temperature_id_seq'::regclass),\n"
                        + "    temperature real,\n" + "    humidity real,\n" + "    sensor_id bigint,\n"
                        + "    timestamp_id bigint NOT NULL,\n"
                        + "    CONSTRAINT temperature_data_pkey PRIMARY KEY (id),\n"
                        + "    CONSTRAINT temperature_data_timestamp_id_fkey FOREIGN KEY (timestamp_id)\n"
                        + "        REFERENCES ohdm.sensor_timestamp (id) MATCH SIMPLE\n"
                        + "        ON UPDATE NO ACTION\n" + "        " + "        ON DELETE NO ACTION\n" + ")");
        statement.executeUpdate();
        statement.close();
    }

    public void createImportedSensorTable() throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ohdm.imported_sensor\n"
                + "(\n" + "    id bigint NOT NULL DEFAULT nextval('ohdm.imported_sensor_sensor_id_seq'::regclass),\n"
                + "    imported_id bigint,\n" + "    geoobject_id bigint,\n"
                + "    CONSTRAINT sensor_type_pkey PRIMARY KEY (id),\n"
                + "    CONSTRAINT imported_sensor_geoobject_id_fkey FOREIGN KEY (geoobject_id)\n"
                + "        REFERENCES ohdm.geoobject (id) MATCH SIMPLE\n" + "        ON UPDATE NO ACTION\n"
                + "        ON DELETE NO ACTION\n" + ")");
        statement.executeUpdate();
        statement.close();
    }
}
