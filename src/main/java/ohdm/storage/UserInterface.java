package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.User;

public interface UserInterface {
    /** adds user informations to the ohdm.external_users table. 
     * 
     * @author marta
     *
     */
    public interface DataSourceInterface {
        
        /** Checks if user id already exists in table.
         */
        public boolean checkIfIdExists(String systemName ) throws SQLException;
        
        /** adds user informations to the table.
         * 
         * @param name          user name.
         * @param foreignKeyId  holds the primary key from the external_systems table.
         * @return              user id.
         */
        public long addUser(User user, long foreignKeyId) throws SQLException;

    }
}
