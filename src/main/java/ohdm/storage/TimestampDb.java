package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public class TimestampDb implements TimestampInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;

    public TimestampDb(ConnectionDb db) {
        this.db = db;
    }

    public void createTimestampTable() throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("CREATE TABLE ohdm.sensor_timestamps\n" + "(\n"
                + "    id bigint NOT NULL DEFAULT nextval('ohdm.sensor_timestamps_id_seq'::regclass),\n"
                + "    \"timestamp\" time with time zone,\n" + "    sensor_id bigint,\n"
                + "    CONSTRAINT sensor_timestamps_pkey PRIMARY KEY (id),\n"
                + "    CONSTRAINT sensor_timestamps_sensor_id_fkey FOREIGN KEY (sensor_id)\n"
                + "    REFERENCES ohdm.sensor_type (id) MATCH SIMPLE\n" + "        "
                + "    ON UPDATE NO ACTION\n"
                + "    ON DELETE NO ACTION\n" + ")");
        statement.executeUpdate();
        statement.close();
    }

    public void addTimestampData(ParsedData parsedData, int foreignKeySensorId) throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("INSERT INTO ohdm.sensor_timestamps (timestamp, sensor_id) VALUES (?, ?)");
        statement.setDate(1, parsedData.getTimestamp());
        statement.setInt(2, foreignKeySensorId);
        statement.executeUpdate();
    }
}
