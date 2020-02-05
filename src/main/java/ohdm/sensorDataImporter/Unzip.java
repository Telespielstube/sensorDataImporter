package ohdm.sensorDataImporter;

import java.io.File;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

public class Unzip {
	
	public Unzip() {}
	
	/** Unzips files to the specified folder.
	 * 
	 * @param paths    path to the folder where the data gets unzipped.
	 * @throws ZipException 
	 */
	public void fileUnzip(File[] fileList, String extractTo) {
		int i = 0;
		System.out.println("Unzipping all files...");
		for (File file : fileList)	{
			System.out.println(i);
			i++;
			try {
			    new ZipFile(file).extractAll(extractTo);
			} catch (ZipException ze) {
			    System.out.println("Caught exception at file:" + file.getName());
			}
			System.out.println(file + " Done");
		}
		System.out.println("Extracting done");
	}
}