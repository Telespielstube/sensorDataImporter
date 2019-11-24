package ohdm.sensorDataImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

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
				System.out.println(values[0] + "    " 
								+ values[1] + "    " 
								+ values[2] + "    " 
								+ values[3] + "    " 
								+ values[4] + "    " 
								+ values[5] + "    " 
								+ values[6] + "    " 
								+ values[7]);
				
			}
		}
	}
}

