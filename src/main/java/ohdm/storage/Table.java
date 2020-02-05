package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Table implements TableInterface {

    private ConnectionDb db;

    public Table(ConnectionDb db) {
        this.db = db;
    }

    public void createFineDustTable() throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("CREATE SEQUENCE IF NOT EXISTS ohdm.fine_dust_data_id_seq;"
                        + "CREATE TABLE IF NOT EXISTS ohdm.fine_dust_data\n" 
                        + "(\n"
                        + "    id bigint NOT NULL DEFAULT nextval('ohdm.fine_dust_data_id_seq'::regclass),\n"
                        + "    pm10 real,\n" 
                        + "    dur_pm10 real,\n" 
                        + "    ratio_pm10 real,\n" 
                        + "    pm25 real,\n"
                        + "    dur_pm25 real,\n" 
                        + "    ratio_pm25 real,\n" 
                        + "    pm0 real,\n"
                        + "    \"timestamp\" timestamp(4) without time zone,\n" 
                        + "    geoobject_id bigint,\n"
                        + "    CONSTRAINT fine_dust_data_pkey PRIMARY KEY (id),\n"
                        + "    CONSTRAINT fine_dust_data_geoobject_id_fkey FOREIGN KEY (geoobject_id)\n"
                        + "        REFERENCES ohdm.geoobject (id) MATCH SIMPLE\n" 
                        + "        ON UPDATE NO ACTION\n"
                        + "        ON DELETE NO ACTION\n" 
                        + "        NOT VALID\n" 
                        + ")");
        statement.executeUpdate();
        statement.close();
    }

    public void createTemperatureTable() throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("CREATE SEQUENCE IF NOT EXISTS ohdm.temperature_data_id_seq;"
                        + "CREATE TABLE IF NOT EXISTS ohdm.temperature_data\n" 
                        + "(\n"
                        + "    id bigint NOT NULL DEFAULT nextval('ohdm.temperature_data_id_seq'::regclass),\n"
                        + "    temperature real,\n" 
                        + "    humidity real,\n"
                        + "    \"timestamp\" timestamp(4) without time zone,\n" 
                        + "    geoobject_id bigint,\n"
                        + "    CONSTRAINT temperature_data_pkey PRIMARY KEY (id),\n"
                        + "    CONSTRAINT temperature_data_geoobject_id_fkey FOREIGN KEY (geoobject_id)\n"
                        + "        REFERENCES ohdm.geoobject (id) MATCH SIMPLE\n" 
                        + "        ON UPDATE NO ACTION\n"
                        + "        ON DELETE NO ACTION\n" 
                        + "        NOT VALID\n" 
                        + ")");
        statement.executeUpdate();
        statement.close();
    }

    public void createAirPressureTable() throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("CREATE SEQUENCE IF NOT EXISTS ohdm.air_pressure_data_id_seq;"
                        + "CREATE TABLE IF NOT EXISTS ohdm.air_pressure_data\n" 
                        + "(\n"
                        + "    id bigint NOT NULL DEFAULT nextval('ohdm.air_pressure_data_id_seq'::regclass),\n"
                        + "    pressure real,\n" 
                        + "    altitude real,\n" 
                        + "    pressure_sealevel real,\n"
                        + "    temperature real,\n" 
                        + "    humidity real,\n"
                        + "    \"timestamp\" timestamp(4) without time zone,\n"
                        + "    geoobject_id bigint,\n" 
                        + "    CONSTRAINT air_pressure_pkey PRIMARY KEY (id),\n"
                        + "    CONSTRAINT air_pressure_geoobject_id_fkey FOREIGN KEY (geoobject_id)\n"
                        + "        REFERENCES ohdm.geoobject (id) MATCH SIMPLE\n" 
                        + "        ON UPDATE NO ACTION\n"
                        + "        ON DELETE NO ACTION\n" 
                        + ")");
        statement.executeUpdate();
        statement.close();
    }

    public void createImportedSensorTable() throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("CREATE SEQUENCE IF NOT EXISTS ohdm.imported_sensor_id_seq;"
                        + "CREATE TABLE IF NOT EXISTS ohdm.imported_sensor\n" + "(\n"
                        + "    id bigint NOT NULL DEFAULT nextval('ohdm.imported_sensor_id_seq'::regclass),\n"
                        + "    imported_id bigint,\n" + "    geoobject_id bigint,\n"
                        + "    CONSTRAINT sensor_type_pkey PRIMARY KEY (id),\n"
                        + "    CONSTRAINT imported_sensor_geoobject_id_fkey FOREIGN KEY (geoobject_id)\n"
                        + "        REFERENCES ohdm.geoobject (id) MATCH SIMPLE\n" + "        ON UPDATE NO ACTION\n"
                        + "        ON DELETE NO ACTION\n" + ")");
        statement.executeUpdate();
        statement.close();
    }
}
