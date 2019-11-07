package ohdm.sensorDataImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.cli.CommandLine;

public class App 
{
	
	private static String extractTo = "/home/martina/extractedLuftdatenArchive";
	
    public static void main( String[] args ) throws FileNotFoundException, IOException 
    {
    	DataBaseConnector connectDb = new DataBaseConnector("jdbc:postgresql://localhost:5432/postgis_ohdm?user=marta&password=0000");
    	Unzip unzip = new Unzip();
    	FileReader fileReader = new FileReader();
    	FileParser fileParser = new FileParser();
    	File[] listOfFiles = null;
    	
    	CommandLine cmdLine = CommandLineParser.parse(args);
    	String path = cmdLine.getOptionValue("i");
    	
		listOfFiles = fileReader.readFile(path);
        unzip.fileUnzip(listOfFiles, extractTo);
        listOfFiles = fileReader.readFile(extractTo);
        fileParser.parseFile(listOfFiles);
        
        connectDb.connectToDb();
    }
}
