package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.Sensor;

public class ImportedSensorDb {

    private ConnectionDb db;
    private ResultSet resultSet = null;
    private long existingId;

    public ImportedSensorDb(ConnectionDb db) {
        this.db = db;
    }

    public void createImportedSensorTable() throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ohdm.imported_sensor\n" + 
                "(\n" + 
                "    id bigint NOT NULL DEFAULT nextval('ohdm.imported_sensor_sensor_id_seq'::regclass),\n" + 
                "    imported_id bigint,\n" + 
                "    geoobject_id bigint,\n" + 
                "    CONSTRAINT sensor_type_pkey PRIMARY KEY (id),\n" + 
                "    CONSTRAINT imported_sensor_geoobject_id_fkey FOREIGN KEY (geoobject_id)\n" + 
                "        REFERENCES ohdm.geoobject (id) MATCH SIMPLE\n" + 
                "        ON UPDATE NO ACTION\n" + 
                "        ON DELETE NO ACTION\n" + 
                ")");
        statement.executeUpdate();
        statement.close();
    }

    public boolean checkIfIdExists(long importedSensorId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT * FROM ohdm.imported_sensor "
                + "WHERE imported_id = " + importedSensorId + ";");
        resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt(1);
            return true;
        }
    }

    public void addImportedSensor(Sensor sensorData, long geoObjectId) throws SQLException {
        if (!checkIfIdExists(sensorData.getImportedSensorId())) {
            PreparedStatement statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.imported_sensor (imported_id, geoobject_id) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sensorData.getImportedSensorId());
            statement.setLong(2, geoObjectId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
        }
    }
}