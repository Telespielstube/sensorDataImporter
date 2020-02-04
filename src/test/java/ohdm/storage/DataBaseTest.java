package ohdm.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import ohdm.bean.Classification;
import ohdm.bean.DataSample;
import ohdm.bean.ExternalSystem;
import ohdm.bean.User;

public class DataBaseTest {

    static ConnectionDb database;
    static ClassificationDb classDb;
    static Classification clazz;
    static UserInfo extSystemDb;
    static ExternalSystem ext;
    static UserDb userDb;
    static User user1;
    long foreignKeyId = 0;
    
    @BeforeClass
    public static void testIfDatabaseGetsCreated() throws IOException, InterruptedException {
        Process p;
        try {
            String[] cmd = { "sh", "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/database/importer.sh" };
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            System.out.println("Created test database.");
        }
    }

    @Before
    public void testIfDatabaseGetsConnected() {
        database = new ConnectionDb("jdbc:postgresql://localhost:5432/postgis_test_db", "marta", "0000");
    }
    
    @Test
    public void testIfClassificationGetsAdded() throws SQLException {
        classDb = new ClassificationDb(database);
        clazz = new Classification("sensor", "temperature");
        foreignKeyId = classDb.addClassification(clazz);
        Assert.assertEquals(true, classDb.checkIfClassificationIdExists(clazz.getSubClassName()));
    }
    
    @Test
    public void testIfExternalSystemGetsAdded() throws SQLException {
        extSystemDb = new UserInfo(database);
        ext = new ExternalSystem("luftdaten", "archive.luftdaten.info");
        foreignKeyId = extSystemDb.addDataSource(ext);
        Assert.assertEquals(true, extSystemDb.checkIfIdExists(ext.getName()));
    }
    
    @Test
    public void testIfUserGetsAdded() throws SQLException {
        userDb = new UserDb(database);
        user1 = new User(1, "Tester");
        userDb.addUser(user1, foreignKeyId);
        Assert.assertEquals(true, userDb.checkIfIdExists(user1.getUserId()));
    }
    
//    @Test
//    public void testIfTemperatureDataGetsAdded() throws SQLException {
//        TemperatureDb tempDb = new TemperatureDb(database);
//        DataSample dataSample = new DataSample(, 56.34);
//        tempDb.addDhtData(dataSample, foreignKeyId);
//        Assert.assertEquals(true, tempDb);
//    }
    
    @AfterClass
    public static void deleteUserEntries() throws SQLException {
        PreparedStatement statement = database.connection.prepareStatement(
                "DELETE FROM ohdm.external_users;");
        statement.executeUpdate();
        System.out.println("Deleted user test entries.");
        Assert.assertEquals(false, userDb.checkIfIdExists(user1.getUserId()));
    }
    
    @AfterClass
    public static void deleteExtSystemEntries() throws SQLException {
        PreparedStatement statement = database.connection.prepareStatement(
                "DELETE FROM ohdm.external_systems WHERE name = ?");
        statement.setString(1, ext.getName());
        statement.executeUpdate();
        System.out.println("Deleted system test entries.");
        Assert.assertEquals(false, extSystemDb.checkIfIdExists(ext.getName()));
    }
    
    @AfterClass
    public static void closeConnection() throws SQLException {
        try {
            database.connection.close();
        } finally {
            // nothing
        }
    }
}
