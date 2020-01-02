package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.User;

public interface UserInterface {
   
    /** Checks if user id already exists in table.
    */
    public boolean checkIfIdExists(int userId, String username) throws SQLException;
        
        /** adds user informations to the table.
         * 
         * @param name          user name.
         * @param foreignKeyId  holds the primary key from the external_systems table.
         * @return              user id.
         */
        public long addUser(User user, long foreignKeyId) throws SQLException;

    }

