package ohdm.sensorDataImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.cli.CommandLine;

import ohdm.parser.*;

public class App 
{
	
	private static String extractTo = "/home/martina/extractedLuftdatenArchive";
	
    public static void main( String[] args ) throws FileNotFoundException, IOException 
    {
       	Unzip unzip = new Unzip();
    	Reader fileReader = new Reader();
    	Parser fileParser = new Parser();
    	File[] listOfFiles = null;
    	
    	CommandLine cmdLine = CommandLineParser.parse(args);
    	String path = cmdLine.getOptionValue("i");
    	
		listOfFiles = fileReader.readFile(path);
        unzip.fileUnzip(listOfFiles, extractTo);
        listOfFiles = fileReader.readFile(extractTo);
        fileParser.parseFile(listOfFiles);
    }
}
