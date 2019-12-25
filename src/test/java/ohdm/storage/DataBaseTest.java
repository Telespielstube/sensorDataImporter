package ohdm.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
        database = new ConnectionDb("jdbc:postgresql://localhost:5432/postgis_ohdm", "marta", "0000");
    }
    
    @Test
    public void testIfExternalSystemGetsAdded() throws SQLException {
        ExternalSystemDb extSystem = new ExternalSystemDb(database);
        ExternalSystem ext = new ExternalSystem("luftdaten", "archive.luftdaten.info");
        foreignKeyId = extSystem.addDataSource(ext);
    }
    
    @Test
    public void testIfUserGetsAdded() throws SQLException {
        UserDb userDb = new UserDb(database);
        User user1 = new User(1, "firstTester");
        userDb.addUser(user1, foreignKeyId);
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
