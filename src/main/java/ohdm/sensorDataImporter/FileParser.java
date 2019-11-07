package ohdm.sensorDataImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import net.lingala.zip4j.core.ZipFile;

public class FileParser {

	public FileParser() {}
	private String fileToParse = "";
	
	/**
	 * 
	 * @param file
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void parseFile(File[] listOfFiles) throws FileNotFoundException, IOException {
		
		Reader input = new FileReader(fileToParse);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(input);
		for (CSVRecord record : records) {
		   System.out.println(record.get("id"));
		}

	} 
}
