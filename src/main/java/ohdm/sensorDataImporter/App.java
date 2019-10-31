package ohdm.sensorDataImporter;

import java.io.IOException;
import org.apache.commons.cli.CommandLine;

public class App 
{
    public static void main( String[] args ) 
    {
    	CommandLine cmdLine = CommandLineParser.parse(args);
    	String path = cmdLine.getOptionValue("i");
    	FileReader fileReader = new FileReader(path);
        try {
        	fileReader.readFile();
        } catch (IOException ex) {
        	System.err.println(ex);
        }
    }
}
