package ohdm.sensorDataImporter;

import java.io.File;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

public class Unzip {
	
	public Unzip() {}
	
	/** Unzips files to the specified folder.
	 * 
	 * @param paths    path to the folder where the data gets unzipped.
	 */
	public void fileUnzip(File[] fileList, String extractTo) {
		int i = 0;
		System.out.println("Unzipping all files...");
		for (File file : fileList)	{
			System.out.println(i);
			i++;
		try {
			new ZipFile(file).extractAll(extractTo);
			System.out.println(file + " Done");
		} catch (ZipException e) {
			e.printStackTrace();
		}
		}
		System.out.println("Done");
	}
}