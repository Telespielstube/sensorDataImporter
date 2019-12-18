package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.ExternalSystem;

/** adds The data source to the ohdm.external_system table. 
 * 
 * @author marta
 *
 */
public interface ExternalSystemInterface {
    
    /** Checks if data source already exists in table.
     */
    public boolean checkIfIdExists(String systemName ) throws SQLException;
    
    /** adds the data source to the table.
     */
    public long addDataSource(ExternalSystem dataSource) throws SQLException;

}
