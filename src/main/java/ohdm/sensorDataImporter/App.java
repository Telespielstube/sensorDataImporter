package ohdm.sensorDataImporter;

import org.apache.commons.cli.CommandLine;;
public class App 
{
    public static void main( String[] args )
    {
    	
    	CommandLine cmdLine = CommandLineParser.parse(args);
    	String pathToDir = cmdLine.getOptionValue("-i");
        
    }
}
