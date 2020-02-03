package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.User;

public class UserDb implements UserInterface {
    private ConnectionDb db;
    private ResultSet resultSet = null;
    private long existingId;

    public UserDb(ConnectionDb db) {
        this.db = db;
    }

    public boolean checkIfIdExists(int userId) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT * FROM ohdm.external_users "
                + "WHERE userid = " + userId + ";");
        resultSet = statement.executeQuery();
        resultSet.next();
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingId = resultSet.getInt("id");
            return true;
        }
    }

    public long addUser(User user, long extSystemId) throws SQLException {
        long userId;
        if (!checkIfIdExists(user.getUserId())) {
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