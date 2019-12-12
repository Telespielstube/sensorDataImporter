package ohdm.storage;

import java.sql.SQLException;
import java.util.ArrayList;

import ohdm.sensorDataImporter.ParsedData;

public class DatabaseManager {
    
    private ArrayList<ParsedData> dataList;
    private DBConnection database = new DBConnection("jdbc:postgresql://localhost:5432/postgis_ohdm", "marta", "0000");
    private SensorTypeDb sensorType = new SensorTypeDb(database);
    private TemperatureDb tempDb = new TemperatureDb(database);
    private FineDustDb fineDustDb = new FineDustDb(database);;
    
    public DatabaseManager(ArrayList<ParsedData> dataList) {
        this.dataList = dataList;
    }
       
    public void selectSensorType() throws SQLException {
        for (int i = 0; i < dataList.size(); ++i) {
            if(dataList.get(i).getSensorType().contains("DHT")) {
                System.out.println(dataList.get(i).getImportedSensorId() + ", " + dataList.get(i).getSensorType() + ", " + dataList.get(i).getValue1() + ", " + dataList.get(i).getValue2());
                int foreignKeySensorId = sensorType.addSensorType(dataList.get(i));    
                tempDb.addDhtData(dataList.get(i), foreignKeySensorId);                
            }
            if(dataList.get(i).getSensorType().contains("PPD")) {
                System.out.println(dataList.get(i).getImportedSensorId() + ", " + dataList.get(i).getSensorType() + ", " + dataList.get(i).getValue1() + ", " + dataList.get(i).getValue2());
                int foreignKeySensorId = sensorType.addSensorType(dataList.get(i));
                fineDustDb.addPpdData(dataList.get(i), foreignKeySensorId);
            }
        }
    }
}

