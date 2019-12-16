package ohdm.storage;

import java.sql.ResultSet;

public class DataSourceDb implements DataSourceInterface {
    
    private DBConnection db;
    private ResultSet resultSet = null;
    private int previousSensorId;
    
    public DataSourceDb(DBConnection db) {
        this.db = db;
    }

    @Override
    public void checkIfDataSourceIdIsPrensent() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addDataSource() {
        // TODO Auto-generated method stub
        
    }

}
