package ohdm.storage;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import ohdm.bean.Classification;
import ohdm.bean.ExternalSystem;
import ohdm.bean.Sensor;
import ohdm.bean.User;
import ohdm.storage.sensorType.*;

public class DatabaseManager {
  
    private ArrayList<Sensor> sensorDataList;
    private ConnectionDb database = new ConnectionDb("jdbc:postgresql://localhost:5432/postgis_ohdm", "marta", "0000"); 
    private Table table = new Table(database);
    private UserInfo userInfo = new UserInfo(database);
    private Dht22 dht22 = new Dht22(database);
    private Ppd42 ppd42 = new Ppd42(database);
    private Sds011 sds011 = new Sds011(database);
    private Sht31 sht31 = new Sht31(database);
    private Bmp180 bmp180 = new Bmp180(database);
    private Bmp280 bmp280 = new Bmp280(database);
    private Bme280 bme280 = new Bme280(database);
    private Htu21 htu21 = new Htu21(database);
    private Hpm hpm = new Hpm(database);
    private Pms1003 pms1003 = new Pms1003(database);
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
        table.createFineDustTable();
        table.createTemperatureTable();
        table.createAirPressureTable();
        table.createImportedSensorTable();
    }

    /** Depending on the .csv column sensor type data gets inserted to the appropriate table.
     * 
     * @throws SQLException     is thrown if an sql insertion fails.
     * @throws ParseException   is thrown if a parsing error occurs.
     */
    public void insertSensorIntoDatabase() throws SQLException, ParseException {
        int typeId = 1; // 1 is the classification for points (location) in ohdm and applies for all sensors.
                
        System.out.println("Inserting sensor data...");
        Classification clazz = new Classification("sensor");
        ExternalSystem dataSource = new ExternalSystem("luftdaten", "archive.luftdaten.info");
        User user = new User(1, "LuftdatenImporter");     
        long extSystemId = userInfo.addDataSource(dataSource);
        long userId = userInfo.addUser(user, extSystemId);
     
        // Here is the section to add sensors.
        for (int i = 0; i < sensorDataList.size(); ++i) {
            if (sensorDataList.get(i).getSensorType().contains("DHT")) {  
                dht22.addDhtData(sensorDataList.get(i), clazz, typeId, userId);
            }
            
            if (sensorDataList.get(i).getSensorType().contains("PPD")) {
                ppd42.addPpdData(sensorDataList.get(i), clazz, typeId, userId);
            }
            
            if (sensorDataList.get(i).getSensorType().contains("SDS")) {
                sds011.addSdsData(sensorDataList.get(i), clazz, typeId, userId);
            }
            
            if (sensorDataList.get(i).getSensorType().contains("SHT")) {  
                sht31.addShtData(sensorDataList.get(i), clazz, typeId, userId);
            }
            
            if (sensorDataList.get(i).getSensorType().contains("BMP180")) {
                bmp180.addBmpData(sensorDataList.get(i), clazz, typeId, userId);
            }
            
            if (sensorDataList.get(i).getSensorType().contains("BMP280")) {
                bmp280.addBmpData(sensorDataList.get(i), clazz, typeId, userId);
            }
            
            if (sensorDataList.get(i).getSensorType().contains("BME")) {
                bme280.addBmeData(sensorDataList.get(i), clazz, typeId, userId);
            }
            
            if (sensorDataList.get(i).getSensorType().contains("HPM")) {
                hpm.addHpmData(sensorDataList.get(i), clazz, typeId, userId);
            }
            
            if (sensorDataList.get(i).getSensorType().contains("HTU")) {
                htu21.addHtuData(sensorDataList.get(i), clazz, typeId, userId);
            }
        }
    }
}

