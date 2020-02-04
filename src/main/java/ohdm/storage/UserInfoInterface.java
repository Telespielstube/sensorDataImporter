package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.ExternalSystem;
import ohdm.bean.User;

/**
 * adds The data source to the ohdm.external_system table.
 * 
 * @author marta
 *
 */
public interface UserInfoInterface {

    /**
     * Checks if data source already exists in table.
     */
    public boolean checkIfIdExists(String entryName) throws SQLException;

    /**
     * adds the data source to the table.
     */
    public long addDataSource(ExternalSystem dataSource) throws SQLException;

    /**
     * adds user informations to the table.
     * 
     * @param name         user name.
     * @param foreignKeyId holds the primary key from the external_systems table.
     * @return user id.
     */
    public long addUser(User user, long foreignKeyId) throws SQLException;

}
