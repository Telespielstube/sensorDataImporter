package ohdm.parser;

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
				System.out.println(values[7]);
				
			}
		}
	}
}

