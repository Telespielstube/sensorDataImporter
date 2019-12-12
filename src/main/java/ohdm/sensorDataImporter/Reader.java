package ohdm.sensorDataImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

public class Reader {
	
	public Reader() {}
	
	/**
	 * Gets all files from folder.
	 * 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public File[] readFile(String path, final String type) throws FileNotFoundException, IOException {
		System.out.println("Reading all files...");
		File folder = new File(path); 
        File[] fileList = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return name.endsWith(type);
            }
            
        }); 
		System.out.println("Done");
		
		return fileList;
	}
}
