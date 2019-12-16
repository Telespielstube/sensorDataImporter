package ohdm.storage;

import java.sql.SQLException;
import java.util.ArrayList;

import ohdm.sensorDataImporter.ParsedData;

public class DatabaseManager {
    
    private ArrayList<ParsedData> dataList;
    private DBConnection database = new DBConnection("jdbc:postgresql://localhost:5432/postgis_ohdm", "marta", "0000");
    private DataSourceDb dataSourceDb = new DataSourceDb(database);
    private SensorTypeDb sensorTypeDb = new SensorTypeDb(database);
    private ClassificationDb classificationDb = new ClassificationDb(database);
    private TemperatureDb tempDb = new TemperatureDb(database);
    private FineDustDb fineDustDb = new FineDustDb(database);
    
    /** Constructor
     * 
     * @param dataList  holds the complete list of parsed data from the csv files.
     */
    public DatabaseManager(ArrayList<ParsedData> dataList) {
        this.dataList = dataList;
    }
    
    /** Methode to create all sensor relevant in the ohdm postgis database.
     */
    public void createTables() {
        sensorTypeDb.createSensorTypeTable();
        tempDb.createTemperatureTable();
        fineDustDb.createFineDustTable();
    }
    
    /** Depending on the .csv column sensor type data gets inserted to the apprpiate table.
     * 
     * @throws SQLException     is thrown if an sql insertion fails.
     */
    public void selectSensorType() throws SQLException {
        for (int i = 0; i < dataList.size(); ++i) {
            if(dataList.get(i).getSensorType().contains("DHT")) {
                System.out.println(dataList.get(i).getImportedSensorId() + ", " + dataList.get(i).getSensorType() + ", " + dataList.get(i).getValue1() + ", " + dataList.get(i).getValue2());
                int foreignKeySensorId = sensorTypeDb.addSensorType(dataList.get(i));    
                tempDb.addDhtData(dataList.get(i), foreignKeySensorId); 
                classificationDb.addClassification();
            }
            if(dataList.get(i).getSensorType().contains("PPD")) {
                System.out.println(dataList.get(i).getImportedSensorId() + ", " + dataList.get(i).getSensorType() + ", " + dataList.get(i).getValue1() + ", " + dataList.get(i).getValue2());
                int foreignKeySensorId = sensorTypeDb.addSensorType(dataList.get(i));
                fineDustDb.addPpdData(dataList.get(i), foreignKeySensorId);
                classificationDb.addClassification();
            }
        }
    }
}

