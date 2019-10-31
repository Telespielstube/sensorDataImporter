package ohdm.sensorDataImporter;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
	FileParser fileParser = new FileParser();
	Unzipper unzipper = new Unzipper();
	private String path = "";

	
	FileReader(String path) {
		this.path = path;
	}
	
	public void readFile() {
		File folder = new File(path); 
		File[] fileList = folder.listFiles();
		
		for (File file : fileList)
		//	System.out.println(file);
			unzipper.fileUnzip(file);
	}
	
	
}
