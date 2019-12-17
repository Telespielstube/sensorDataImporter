package ohdm.storage;

import java.sql.SQLException;
import java.util.ArrayList;

import ohdm.sensorDataImporter.ParsedData;
import ohdm.bean.Classification;

public class DatabaseManager {
    

    private ArrayList<ParsedData> dataList;
    private Classification classification;
    private ConnectionDb database = new ConnectionDb("jdbc:postgresql://localhost:5432/postgis_ohdm", "marta", "0000");
    private DataSourceDb dataSourceDb = new DataSourceDb(database);
    private SensorTypeDb sensorTypeDb = new SensorTypeDb(database);
    private ClassificationDb classificationDb = new ClassificationDb(database);
    private TemperatureDb tempDb = new TemperatureDb(database);
    private FineDustDb fineDustDb = new FineDustDb(database);
    private TimestampDb timestampDb = new TimestampDb(database);
    
    /** Constructor
     * 
     * @param dataList  holds the complete list of parsed data from the csv files.
     */
    public DatabaseManager(ArrayList<ParsedData> dataList) {
        this.dataList = dataList;
    }
    
    /** Methode to create all sensor relevant in the ohdm postgis database.
     * @throws SQLException     thrown if an error occurs during the table creation.
     */
    public void createTables() throws SQLException {
        sensorTypeDb.createSensorTypeTable();
        tempDb.createTemperatureTable();
        fineDustDb.createFineDustTable();
        timestampDb.createTimestampTable();
    }

    /** Depending on the .csv column sensor type data gets inserted to the apprpiate table.
     * 
     * @throws SQLException     is thrown if an sql insertion fails.
     */
    public void selectSensorType() throws SQLException {
        classification.setClassification("sensor");
        int foreignKeySensorId = 0;
        for (int i = 0; i < dataList.size(); ++i) {
            if(dataList.get(i).getSensorType().contains("DHT")) {
                classification.setSubClassificationName("temperature");
                classificationDb.addClassification(classification.getClassification(), classification.getSubClassName());
                foreignKeySensorId = sensorTypeDb.addSensorType(dataList.get(i));    
                timestampDb.addTimestampData(dataList.get(i), foreignKeySensorId);
                tempDb.addDhtData(dataList.get(i), foreignKeySensorId); 
            }
            if(dataList.get(i).getSensorType().contains("PPD")) {
                classification.setSubClassificationName("fine dust");
                classificationDb.addClassification(classification.getClassification(), classification.getSubClassName());
                foreignKeySensorId = sensorTypeDb.addSensorType(dataList.get(i));
                timestampDb.addTimestampData(dataList.get(i), foreignKeySensorId);
                fineDustDb.addPpdData(dataList.get(i), foreignKeySensorId);               
            }
        }
    }
}

