package ohdm.storage;

import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public interface TemperatureInterface {
    public void addDhtData(ParsedData tempData, int foreignKeySensorId) throws SQLException;
}
