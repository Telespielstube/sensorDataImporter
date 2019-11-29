package ohdm.sensorDataImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

	
    
    ArrayList<ParsedData> parsedDataList = new ArrayList<>();
	public Parser() {}
	
	/** Parses DHT22 and PPD42 sensor data.
	 * 
	 * @param file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ArrayList<ParsedData> parseFile(File[] listOfFiles) {
	    
		String delimiter = ";";
		String row = null;
		BufferedReader csv = null;
		String[] values = null;
		// int i =0;
		for (File file : listOfFiles) {
			try {
			    if (file.getName().endsWith(".csv")) {
			        csv = new BufferedReader(new FileReader(file));
			    } else {
			        continue;
			    }
			} catch (FileNotFoundException e) {
                System.err.println("No files were found.");
            }
			try {
                while ((row = csv.readLine()) != null) {	
                    values = row.split(delimiter);
                	if (values[0].equals("sensor_id")) 
                	    continue;
                    ParsedData parsedData = new ParsedData();
                	if (values[1].contains("DHT")) {
                	    parseDHTData(values, parsedData);
                	} else if (values[1].contains("PPD")) {
                	    parsePPDData(values, parsedData);
                	}
                }
            } catch (NumberFormatException | IOException e) {
                System.err.println("Something went wrong while reading/writing data.");
            }
		//	i++;
		}	
		return parsedDataList;
	}
	
	public void parseDHTData(String[] values, ParsedData parsedData) {
	    if (values.length == 8 ) {
	        parsedData.setSensorId(Integer.valueOf(values[0]));
	        parsedData.setSensorType(values[1]);
	        parsedData.setLocation(Integer.valueOf(values[2]));
	        parsedData.setLatitude(Float.valueOf(values[3]));
	        parsedData.setLongitude(Float.valueOf(values[4]));
	        parsedData.setTimestamp(values[5]);
	        parsedData.setValue1(Float.valueOf(values[6]));
	        parsedData.setValue2(Float.valueOf(values[7]));
	        parsedDataList.add(parsedData);
	    }
    }

    public void parsePPDData(String[] values, ParsedData parsedData) {
        parsedData.setSensorId(Integer.valueOf(values[0]));
        parsedData.setSensorType(values[1]);
        parsedData.setLocation(Integer.valueOf(values[2]));
        parsedData.setLatitude(Float.valueOf(values[3]));
        parsedData.setLongitude(Float.valueOf(values[4]));
        parsedData.setTimestamp(values[5]);
        parsedData.setValue1(Float.valueOf(values[6]));
        parsedData.setValue2(Float.valueOf(values[7]));
        parsedData.setValue4(Float.valueOf(values[8]));
        parsedData.setValue5(Float.valueOf(values[9]));
        parsedData.setValue6(Float.valueOf(values[10]));
        parsedData.setValue7(Float.valueOf(values[11]));
        parsedDataList.add(parsedData);
    }
    
    // add more sensor parser.
}

