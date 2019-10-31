package ohdm.sensorDataImporter;

import java.io.Reader;

import org.apache.commons.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class FileParser {

	public void parseFile() {
		Reader csvFile = new FileReader(null);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(csvFile);
		for (CSVRecord record : records) {
		    String columnOne = record.get(0);
		    String columnTwo = record.get(1);
		}
	}
}
