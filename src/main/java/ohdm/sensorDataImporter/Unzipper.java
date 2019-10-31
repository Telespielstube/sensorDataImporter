package ohdm.sensorDataImporter;

import java.io.File;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;

public class Unzipper {

	/**
	 * 
	 * @param paths
	 */
	public void fileUnzip(File file) {
		String extractTo = "/home/martina/extractedLuftdatenArchive";
		ZipFile unzip;
		
		try {
			unzip = new ZipFile(file.getAbsolutePath());
			unzip.extractAll(extractTo);
			System.out.println(extractTo);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
}
