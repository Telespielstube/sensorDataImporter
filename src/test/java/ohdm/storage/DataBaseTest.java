package ohdm.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Rule;
import org.junit.Test;

import com.opentable.db.postgres.embedded.DatabaseConnectionPreparer;
import com.opentable.db.postgres.embedded.DatabasePreparer;
import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;

import ohdm.sensorDataImporter.ParsedData;

public class DataBaseTest {

    private ParsedData sensorType1 = new ParsedData.Builder(1, "DHT", 19, (float) 48.13, (float) 90.78, "12:45:45").build();
    private ParsedData sensorType2 = new ParsedData.Builder(1, "DHT", 19, (float) 48.00, (float) 80.78, "12:05:22").build();
    private ParsedData sensorTxype3 = new ParsedData.Builder(1, "PPD", 19, (float) 44.10, (float) 30.78, "12:35:15").build();
    private ResultSet resultSet = null;

    private final DatabasePreparer sensorTypeDB = new SensorTypePreparer("sensor_type");


    @Rule
    public PreparedDbRule dbA1 = EmbeddedPostgresRules.preparedDatabase(sensorTypeDB);

    @Test
    public void testDbs() throws Exception {
        try (Connection c = dbA1.getTestDatabase().getConnection(); 
            Statement stmt = c.createStatement()) {
            stmt.execute("INSERT INTO sensor_type VALUES(1)");
            resultSet = stmt.executeQuery("SELECT COUNT(1) FROM sensor_type");
            resultSet.next();
            assertEquals(1, resultSet.getInt(1));
        }
    }
    
    // Creates test tables.
    static class SensorTypePreparer implements DatabaseConnectionPreparer {
        private final String name;

        public SensorTypePreparer(String name) {
            this.name = name;
        }

        @Override
        public void prepare(Connection conn) throws SQLException {
            try (PreparedStatement stmt = conn.prepareStatement(String.format("CREATE TABLE sensor_type(sensor_id bigint, imported_id bigint, sensor_type text)"))) {
                stmt.execute();
            }
        }
    }
    }
