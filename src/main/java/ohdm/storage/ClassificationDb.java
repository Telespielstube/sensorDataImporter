package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassificationDb implements ClassificationInterface {

    private DBConnection db;
    private ResultSet resultSet = null;
    private String existingClassification = null;

    public ClassificationDb(DBConnection db) {
        this.db = db;
    }
    
    @Override
    public boolean checkIfClassificationIdExists(String classification) {
        PreparedStatement statement = db.connection.prepareStatement("SELECT * FROM ohdm.classification WHERE class = " + classification + ";");
        resultSet = statement.executeQuery();
        resultSet.next();    
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingClassification = resultSet.getString(2);
            return true;
        }
    }
    
    @Override
    public void addClassification(String classification, String subClassName) {
        int returnId;
        if (!checkIfClassificationIdExists(classification) {
            System.out.println("INSERT INTO ohdm.classification (class, subclassname) VALUES("'" + parsedData.getSensorType() + "')");
            PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.classification (class, subclassname) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, parsedData.getImportedSensorId());
            statement.setString(2, parsedData.getSensorType());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            returnId = resultSet.getInt("sensor_id");
        } else {
            returnId = previousSensorId;
        }
        return returnId;      
    }
        
    }
}
