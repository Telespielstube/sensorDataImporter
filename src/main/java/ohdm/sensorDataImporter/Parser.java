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
                	if (values[0].equals("sensor_id") || values[3].isBlank() || values[4].isBlank() || values[5].isBlank()) {
                	    continue;
                	} 
                    ParsedData parsedData = new ParsedData(Integer.valueOf(values[0]), 
                            values[1], 
                            Integer.valueOf(values[2]), 
                            Float.valueOf(values[3]), 
                            Float.valueOf(values[4]), 
                            values[5]);
                	if (values[1].contains("DHT")) {
                	    parseDhtData(values, parsedData);
                	} else if (values[1].contains("PPD")) {
                	    parsePpdData(values, parsedData);
                	}
                }
            } catch (IOException e) {
                System.err.println("Something went wrong while reading/writing data.");  
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
		}	
		return parsedDataList;
	}
	
	public void parseDhtData(String[] values, ParsedData parsedData) {
	    if (values.length == 8 ) {
	        parsedData.setValue1(Float.valueOf(values[6]));
	        parsedData.setValue2(Float.valueOf(values[7]));
	        parsedDataList.add(parsedData);
	    }
    }

    public void parsePpdData(String[] values, ParsedData parsedData) {
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

