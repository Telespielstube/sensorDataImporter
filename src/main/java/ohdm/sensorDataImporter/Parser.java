package ohdm.sensorDataImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import ohdm.bean.DataSample;
import ohdm.bean.Sensor;

public class Parser {
    ArrayList<Sensor> sensorList = new ArrayList<>();
    ArrayList<DataSample> dataSampleList = new ArrayList<>();
   // HashMap<Sensor, ArrayList<DataSample>> SensorMap = new HashMap<Sensor, ArrayList<DataSample>>(); 
    
    public Parser() {
    }

    /**
     * Parses sensor data of the csv file.
     * 
     * @param file                      sensor data csv file.
     * @throws NumberFormatException    thrown if some convertion fails e.g. String into a number.
     * @throws IOException              thrown if an read write error occurs.
     * @throws FileNotFoundException    thrown if a file is not found at the specified path.
     * @return                          List of Sensors.
     */
    public ArrayList<Sensor> parseFile(File[] listOfFiles) throws NumberFormatException, IOException {
        String delimiter = ";";
        String row = null;
        BufferedReader csv = null;
        String[] values = null;
        for (File file : listOfFiles) {
            if (file.getName().endsWith(".csv")) {
                csv = new BufferedReader(new FileReader(file));
            } else {
                continue;
            }
            while ((row = csv.readLine()) != null) {
                values = row.split(delimiter);

                if (values[0].equals("sensor_id")) {
                    continue;
                }
                Sensor sensorData = new Sensor(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]),
                        Float.valueOf(values[3]), Float.valueOf(values[4]), values[5]);
                if (values[1].contains("dht")) {
                    parseDhtData(values, sensorData);
                } 
                if (values[1].contains("ppd")) {
                    parsePpdData(values, sensorData);
                }
            }
        }
        return sensorList;
    }

    /**
     * Parses data for the dht sensor.
     * 
     * @param values     columns for temperature and humidity.
     * @param parsedData Object where the data gets added to.
     */
    public void parseDhtData(String[] values, Sensor parsedData) {
        if (values.length == 7) {
            parsedData.setValue1(Float.valueOf(values[6]));
            parsedData.setValue2(Float.valueOf(values[7]));
            sensorList.add(parsedData);
        }
    }

    /**
     * Parses the relevant data for the ppd sensor.
     * 
     * @param values     columns for pm10, duration_pm10, ratio_pm10 and pm25,
     *                   duration_pm25, ratio_pm25.
     * @param parsedData Object where the data gets added to.
     */
    public void parsePpdData(String[] values, Sensor parsedData) {
        parsedData.setValue1(Float.valueOf(values[6]));
        parsedData.setValue2(Float.valueOf(values[7]));
        parsedData.setValue4(Float.valueOf(values[8]));
        parsedData.setValue5(Float.valueOf(values[9]));
        parsedData.setValue6(Float.valueOf(values[10]));
        parsedData.setValue7(Float.valueOf(values[11]));
        sensorList.add(parsedData);
    }

    // add more sensor parser.
}
