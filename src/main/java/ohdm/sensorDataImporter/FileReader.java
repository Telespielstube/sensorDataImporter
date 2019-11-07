package ohdm.sensorDataImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
	FileParser fileParser = new FileParser();
	
	public FileReader() {	}
	
	/**
	 * Gets all 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public File[] readFile(String path) throws FileNotFoundException, IOException {
		System.out.println("Reading all files...");
		File folder = new File(path); 
		File[] fileList = folder.listFiles();
		System.out.println("Done");
		
		return fileList;
	}
	
	
}
