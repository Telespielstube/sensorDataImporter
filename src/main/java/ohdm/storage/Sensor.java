package ohdm.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ohdm.bean.Classification;

public class Sensor implements SensorInterface {

    private ConnectionDb db;
    private ResultSet resultSet = null;
    private int existingClassificationId = 0;

    public Sensor(ConnectionDb db) {
        this.db = db;
    }
    
    @Override
    public long addClassification(Classification clazz) throws SQLException {
        long classId;
        if (!checkIfClassificationIdExists(clazz.getSubClassName())) {
            PreparedStatement statement = db.connection.prepareStatement("INSERT INTO ohdm.classification "
                    + "(class, subclassname) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, clazz.getClassification());
            statement.setString(2, clazz.getSubClassName());
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
