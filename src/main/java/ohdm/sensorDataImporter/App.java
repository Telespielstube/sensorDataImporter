package ohdm.sensorDataImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;

import ohdm.storage.DatabaseManager;

public class App 
{
	// Added new command line option u -> extractTo is not needed anymore
	//private static String extractTo = "C:\\test";
	//private static String extractTo = "/Users/marta/extractedLuftdaten";
    
    public static void main( String[] args ) throws FileNotFoundException, IOException, SQLException  {
    	ArrayList<ParsedData> dataList = new ArrayList<>();

       	Unzip unzip = new Unzip();
    	Reader fileReader = new Reader();
    	Parser fileParser = new Parser();
    	File[] listOfFiles = null;
    	
    	CommandLine cmdLine = CommandLineParser.parse(args);
    	String path = cmdLine.getOptionValue("i");
    	String extractTo = cmdLine.getOptionValue("u");
    	
		listOfFiles = fileReader.readFile(path, ".zip");
        unzip.fileUnzip(listOfFiles, extractTo);
        listOfFiles = fileReader.readFile(extractTo, ".csv");
        dataList = fileParser.parseFile(listOfFiles);
        
        //Database 
        DatabaseManager databaseManager = new DatabaseManager(dataList);
        databaseManager.createTables();
        databaseManager.selectSensorType();
    }
}
