package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.ExternalSystem;
import ohdm.bean.Sensor;

public class ExternalSystemDb implements ExternalSystemInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;
    private long existingId;

    public ExternalSystemDb(ConnectionDb db) {
        this.db = db;
    }

    public boolean checkIfIdExists(String systemName) throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("SELECT * FROM ohdm.external_systems WHERE name = '" + systemName + "';");
        resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt(1);
            return true;
        }
    }

    public long addDataSource(ExternalSystem dataSource) throws SQLException {
        long returnId;
        if (!checkIfIdExists(dataSource.getName())) {
            PreparedStatement statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.external_systems (name, description) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dataSource.getName());
            statement.setString(2, dataSource.getDescription());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            returnId = resultSet.getInt("id");
        } else {
            returnId = existingId;
        }
        return returnId;
    }
}
