package ohdm.sensorDataImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;

import ohdm.bean.Sensor;
import ohdm.storage.DatabaseManager;

public class App 
{   
    public static void main( String[] args ) throws FileNotFoundException, IOException, SQLException  {
    	ArrayList<Sensor> dataList = new ArrayList<>();

       	Unzip unzip = new Unzip();
    	Reader fileReader = new Reader();
    	Parser fileParser = new Parser();
    	File[] listOfFiles = null;
    	
    	// Command line parameter parsing
    	CommandLine cmdLine = CommandLineParser.parse(args);
    	String path = cmdLine.getOptionValue("i");
    	String extractTo = cmdLine.getOptionValue("u");
    	
    	// SEnsor data parsing
		listOfFiles = fileReader.readFile(path, ".zip");
        unzip.fileUnzip(listOfFiles, extractTo);
        listOfFiles = fileReader.readFile(extractTo, ".csv");
        dataList = fileParser.parseFile(listOfFiles);
        
        //Database operations
        DatabaseManager databaseManager = new DatabaseManager(dataList);
        databaseManager.createTables();
        databaseManager.insertSensorIntoDatabase();
    }
}
