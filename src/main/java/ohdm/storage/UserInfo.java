package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.ExternalSystem;
import ohdm.bean.Sensor;
import ohdm.bean.User;

public class UserInfo implements UserInfoInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;
    private long existingId;

    public UserInfo(ConnectionDb db) {
        this.db = db;
    }

    public boolean checkIfIdExists(String entryName) throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("SELECT * FROM ohdm.external_systems WHERE name = '" + entryName + "';");
        resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt(1);
            return true;
        }
    }

    public boolean checkIdIdExists(String entryName) throws SQLException {
        PreparedStatement statement = db.connection
                .prepareStatement("SELECT * FROM ohdm.external_users " + "WHERE username = " + entryName + ";");
        resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt("id");
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

    public long addUser(User user, long extSystemId) throws SQLException {
        long userId;
        if (!checkIfIdExists(user.getUserName())) {
            PreparedStatement statement = db.connection.prepareStatement(
                    "INSERT INTO ohdm.external_users (userid, username, external_system_id) VALUES(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setLong(3, extSystemId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            userId = resultSet.getInt("id");
        } else {
            userId = existingId;
        }
        return userId;
    }
}
