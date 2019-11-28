package ohdm.sensorDataImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import ohdm.bean.SensorType;

public class Parser {

	
    ParsedData parsedData = new ParsedData();
    ArrayList<ParsedData> parsedDataList = new ArrayList<>();
	public Parser() {}
	
	/**
	 * 
	 * @param file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ArrayList<ParsedData> parseFile(File[] listOfFiles) throws FileNotFoundException, IOException {
		String delimiter = ";";
		String row = null;
		BufferedReader csv;
		String[] values = null;
		// int i =0;
		for (File file : listOfFiles) {
			csv = new BufferedReader(new FileReader(file));
			while ((row = csv.readLine()) != null) {
				
			    values = row.split(delimiter);
		    	if ( (values[0].equals("sensor_id")) || (values.length < 7) ) 
				    continue;
			//	System.out.println(values[0]+ " " + values[1]+ " " + values[2]+ " " + values[3]+ " " + values[6]+ " " + values[7]);
				parsedData.setSensorId(Integer.valueOf(values[0]));
				parsedData.setSensorType(values[1]);
				parsedDataList.add(new ParsedData(parsedData));
			}
		//	i++;
		}	
		return parsedDataList;
	}
}

