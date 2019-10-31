package ohdm.sensorDataImporter;

import org.apache.commons.cli.CommandLine;

public class App 
{
    public static void main( String[] args ) 
    {
    	CommandLine cmdLine = CommandLineParser.parse(args);
    	String path = cmdLine.getOptionValue("i");
    	FileReader fileReader = new FileReader(path);
        fileReader.readFile();
    }
}
