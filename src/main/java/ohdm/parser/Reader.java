package ohdm.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Reader {
	
	private String fileToRead = null;
	
	public Reader() {}
	
	public Reader(String fileToRead) {
		this.fileToRead = fileToRead;
	}
	
	/**
	 * Gets all files from folder.
	 * 
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
