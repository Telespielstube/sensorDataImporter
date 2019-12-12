package ohdm.storage;

import java.sql.ResultSet;
import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public interface SensorInterface {
    public boolean checkIfIdIsInDatabase(ResultSet resultSet, int importedSensorId) throws SQLException;
    public int addSensorType(ParsedData parsedData) throws SQLException;
}
