package ohdm.sensorDataImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ohdm.bean.SensorData;

public class Parser {

	
    SensorData sensorData = new SensorData();
    ArrayList<SensorData> sensorList = new ArrayList<>();
	public Parser() {}
	
	/**
	 * 
	 * @param file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ArrayList<SensorData> parseFile(File[] listOfFiles) throws FileNotFoundException, IOException {
		String delimiter = ";";
		String row = null;
		BufferedReader csv;
		String[] values = null;
		int i =0;
		for (File f : listOfFiles) {
			csv = new BufferedReader(new FileReader(f));
			while ((row = csv.readLine()) != null) {
				if (i > 0) {
					values = row.split(delimiter);
					
					if (values.length < 7)
						continue;
					//System.out.println(values[0]+ " " + values[1]+ " " + values[5]+ " " + values[6]+ " " + values[7]);
					
					//System.out.println(values[0]+ " " + values[1] + " " + values[5]+ " " + values[6]+ " " + values[7]);
					sensorData.setSensorId(Integer.valueOf(values[0]));
					sensorData.setSensorType(values[1]);
					sensorData.setTimestamp(values[5]);
					sensorData.setTemperature(Float.parseFloat(values[6]));
					sensorData.setHumidity(Float.valueOf(values[7]));
					sensorList.add(new SensorData(sensorData));
				}
				i++;
			}
		}
		return sensorList;
	}
}

