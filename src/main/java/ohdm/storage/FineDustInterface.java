package ohdm.storage;

import java.sql.SQLException;

import ohdm.sensorDataImporter.ParsedData;

public interface FineDustInterface {
    public void addPpdData(ParsedData dustData, int foreignKeySensorId) throws SQLException;
}
