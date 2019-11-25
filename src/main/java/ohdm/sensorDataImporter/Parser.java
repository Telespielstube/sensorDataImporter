package ohdm.sensorDataImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import ohdm.bean.SensorData;

public class Parser {

    SensorData sensorData = new SensorData();
	public Parser() {}
	
	/**
	 * 
	 * @param file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void parseFile(File[] listOfFiles) throws FileNotFoundException, IOException {
		String delimiter = ";";
		String row = null;
		BufferedReader csv;
		String[] values = null;
		
		for (File f : listOfFiles) {
			csv = new BufferedReader(new FileReader(f));
			while ((row = csv.readLine()) != null) {
				values = row.split(delimiter);
				//sensorData.setSensorId(Integer.valueOf(values[0].trim()));
				sensorData.setSensorType(values[1]);
				sensorData.setTimestamp(values[5]);
				sensorData.setTemperature(Float.parseFloat(values[6]));
				sensorData.setHumidity(Float.valueOf(values[7]));
				
			}
		}
	}
}

