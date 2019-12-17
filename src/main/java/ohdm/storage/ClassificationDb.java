package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClassificationDb implements ClassificationInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;
    private int existingClassificationId = 0;

    public ClassificationDb(ConnectionDb db) {
        this.db = db;
    }
    
    @Override
    public boolean checkIfClassificationIdExists(String subClassName) throws SQLException {
        PreparedStatement statement = db.connection.prepareStatement("SELECT COUNT(subclassname) FROM ohdm.classification WHERE subclassname = " + subClassName + ";");
        resultSet = statement.executeQuery();
        resultSet.next();    
        if (resultSet.getRow() == 0) {
            return false;
        } else {
            existingClassificationId = resultSet.getInt("id");
            return true;
        }
    }
    
    @Override
    public int addClassification(String classification, String subClassName) throws SQLException {
        int classId;
        if (!checkIfClassificationIdExists(subClassName)) {
            PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.classification (class, subclassname) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, classification);
            statement.setString(2, subClassName);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            classId = resultSet.getInt("id");
        } else {
            classId = existingClassificationId;
        }
        return classId;
    }
}
