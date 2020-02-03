package ohdm.storage;

import java.sql.SQLException;
import java.util.ArrayList;

import ohdm.bean.Classification;
import ohdm.bean.ExternalSystem;
import ohdm.bean.Sensor;
import ohdm.bean.User;
import ohdm.sensorType.*;

public class DatabaseManager {
    private Dht22 dht = new Dht22();
    private ArrayList<Sensor> sensorDataList;
    private ConnectionDb database = new ConnectionDb("jdbc:postgresql://localhost:5432/postgis_ohdm", "marta", "0000");
    private ExternalSystemDb dataSourceDb = new ExternalSystemDb(database);
    private UserDb userDb = new UserDb(database);
    private GeoObjectDb geoObjectDb = new GeoObjectDb(database); 
    private GeoGeometryDb geoGeometryDb = new GeoGeometryDb(database);
    private ImportedSensorDb sensorDb = new ImportedSensorDb(database);
    private ClassificationDb classificationDb = new ClassificationDb(database);
    private PointsDb pointsDb = new PointsDb(database);
    private TemperatureDb temperatureDb = new TemperatureDb(database);
    private FineDustDb fineDustDb = new FineDustDb(database);
      
    /** Constructor
     * 
     * @param sensorDataList  holds the complete list of parsed data from the csv files.
     */
    public DatabaseManager(ArrayList<Sensor> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }
    
    /** Methode to create all sensor relevant in the ohdm postgis database.
     * @throws SQLException     thrown if an error occurs during the table creation.
     */
    public void createTables() throws SQLException {
        System.out.println("Creating not existing tables...");
        sensorDb.createImportedSensorTable();
        temperatureDb.createTemperatureTable();
        fineDustDb.createFineDustTable();
    }

    /** Depending on the .csv column sensor type data gets inserted to the apprpiate table.
     * 
     * @throws SQLException     is thrown if an sql insertion fails.
     */
    public void insertSensorToDatabase() throws SQLException {
        System.out.println("Inserting sensor data...");
        Classification clazz = new Classification("sensor", "temperature");
        ExternalSystem dataSource = new ExternalSystem("luftdaten", "archive.luftdaten.info");
        User user = new User(1, "LuftdatenImporter");     
        long extSystemId = dataSourceDb.addDataSource(dataSource);
        long userId = userDb.addUser(user, extSystemId);
     
        for (int i = 0; i < sensorDataList.size(); ++i) {
            if(sensorDataList.get(i).getSensorType().contains("DHT")) {               
                long clazzId = classificationDb.addClassification(clazz);               
                long geoObjectId = geoObjectDb.addGeoObject(sensorDataList.get(i), userId);              
                long pointId = pointsDb.addPoint(sensorDataList.get(i), userId);
                sensorDb.addImportedSensor(sensorDataList.get(i), geoObjectId);                   
                temperatureDb.addDhtData(sensorDataList.get(i), geoObjectId); 
             // long geometryId = geoGeometryDb.addGeoGeometry(sensorDataList.get(i), pointId, typeId=1(1 is Klassifikation Punkt), geoObjectId, clazzId, userId);
            }
        }
    }
}

