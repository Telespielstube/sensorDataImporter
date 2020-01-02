package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.Sensor;

public class TimestampDb implements TimestampInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;

    public TimestampDb(ConnectionDb db) {
        this.db = db;
    }

    public void createTimestampTable() throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ohdm.sensor_timestamp\n" + 
                "(\n" + 
                "    id bigint NOT NULL DEFAULT nextval('ohdm.sensor_timestamps_id_seq'::regclass),\n" + 
                "    \"timestamp\" text COLLATE pg_catalog.\"default\",\n" + 
                "    CONSTRAINT sensor_timestamps_pkey PRIMARY KEY (id)\n" + 
                ")");
        statement.executeUpdate();
        statement.close();
    }
    
    public long addTimestampData(Sensor sensorData) throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("INSERT INTO ohdm.sensor_timestamp (timestamp) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, sensorData.getTimestamp());
        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getInt("id");
    }
}