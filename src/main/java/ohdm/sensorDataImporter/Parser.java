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

    // Standard constructor, no parameters.
    public Parser() {}

    /**
     * Parses sensor data of the csv file.
     * 
     * @param file sensor data csv file.
     * @throws NumberFormatException thrown if some conversion fails e.g. String
     *                               into a number.
     * @throws IOException           thrown if an read write error occurs.
     * @throws FileNotFoundException thrown if a file is not found at the specified
     *                               path.
     * @return List of Sensors.
     */
    public ArrayList<Sensor> parseFile(File[] listOfFiles) throws IOException {
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
            String[] headers = null;
            if ((row = csv.readLine()) != null) {
                headers = row.split(delimiter);
            }
            while ((row = csv.readLine()) != null) {

                try {
                    values = row.split(delimiter);
                    // skip rows that have no sensor data
                    if (values.length <= 6)
                        continue;
                    System.out.println(values[1].toString() +", " 
                        + values[2].toString() +", " 
                        + values[3].toString() +", " 
                        + values[4].toString() +", " 
                        + values[4].toString() +", " 
                        + values[5].toString());
                    Sensor sensorData = new Sensor(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]),
                            Float.valueOf(values[3]), Float.valueOf(values[4]), values[5]);
                    for (int i = 6; i < values.length; ++i) {
                        if (values[i].isEmpty()) {
                            continue;
                        }
                        System.out.println(headers[i].toString() + ", " + values[i].toString());
                        sensorData.addDataSample(new DataSample(headers[i], Float.valueOf(values[i])));
                    }
                    sensorList.add(sensorData);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    continue;
                } catch (ArrayIndexOutOfBoundsException exp) {
                    continue;
                }
            }
        }
        return sensorList;
    }
}
