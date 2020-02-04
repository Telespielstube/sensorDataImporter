package ohdm.storage;

import java.sql.SQLException;

public interface TableInterface {

    public void createFineDustTable() throws SQLException;
    public void createTemperatureTable() throws SQLException;
    public void createImportedSensorTable() throws SQLException;
}
