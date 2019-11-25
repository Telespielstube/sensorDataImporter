package ohdm.sensorDataImporter;

import java.io.File;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

public class Unzip {
	
	public Unzip() {}
	
	/**
	 * 
	 * @param paths
	 */
	public void fileUnzip(File[] fileList, String extractTo) {
		System.out.println("Unzipping all files...");
		for (File file : fileList)	
		try {
			new ZipFile(file).extractAll(extractTo);
			System.out.println(file + " Done");
		} catch (ZipException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}