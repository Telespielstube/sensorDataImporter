package ohdm.storage;

import java.sql.SQLException;

import ohdm.bean.DataSource;

/** adds The data source to the ohdm.external_system table. 
 * 
 * @author marta
 *
 */
public interface DataSourceInterface {
    
    /** Checks if data source already exists in table.
     */
    public boolean checkIfIdExists(String systemName ) throws SQLException;
    
    /** adds the data source to the table.
     */
    public long addDataSource(DataSource dataSource) throws SQLException;

}
