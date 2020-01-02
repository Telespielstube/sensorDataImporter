package ohdm.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import ohdm.bean.ExternalSystem;
import ohdm.bean.User;

public class DataBaseTest {

    ConnectionDb database;
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
            //
        }
    }

    @Before
    public void testIfDatabaseGetsConnected() {
        database = new ConnectionDb("jdbc:postgresql://localhost:5432/postgis_test_db", "tester", "0000");
    }
    
    @Test
    public void testIfExternalSystemGetsAdded() throws SQLException {
        ExternalSystemDb extSystemDb = new ExternalSystemDb(database);
        ExternalSystem ext = new ExternalSystem("luftdaten", "archive.luftdaten.info");
        foreignKeyId = extSystemDb.addDataSource(ext);
        Assert.assertEquals(true, extSystemDb.checkIfIdExists(ext.getName()));
    }
    
    @Test
    public void testIfUserGetsAdded() throws SQLException {
        UserDb userDb = new UserDb(database);
        User user1 = new User(1, "firstTester");
        userDb.addUser(user1, foreignKeyId);
        Assert.assertEquals(true, userDb.checkIfIdExists(user1.getUserId()));
    }
    
//    @Test
//    public void testIfTemperatureDataGetsAdded() throws SQLException {
//        TemperatureDb tempDb = new TemperatureDb(database);
//        DataSample datSamp = new DataSample(34.00, 56.34);
//        tempDb.addDhtData(tempData, foreignKeyId);
//        Assert.assertEquals(true, tempDb);
//    }
       
    @Test
    public void testIfFineDustDataGetsAdded() throws SQLException {
        
    }
    
    @After
    public void deleteTestEntries() {
        
    }
    
    @After
    public void closeConnection() throws SQLException {
        try {
            database.connection.close();
        } finally {
            // do nothing
        }
    }

}
