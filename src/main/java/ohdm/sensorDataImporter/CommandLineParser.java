package ohdm.sensorDataImporter;

import java.util.Arrays;

import org.apache.commons.cli.*;

public class CommandLineParser {
	private static Options options;
	private static DefaultParser cmdParser;
	private static HelpFormatter helpFormatter;
	private static CommandLine cmdLine;

	/**
	 * Definition of all available command line arguments.
	 * 
	 * @return command line arguments.
	 */
	private static Options createOptions() {
		Option pathOption = Option.builder("-i").required().hasArg().desc("path to directory.").build();
		options = new Options();
		options.addOption(pathOption);

		return options;
	}

	/**
	 * Parsing command line arguments.
	 *  
	 * @param cmdLineArgs	Command line arguments
	 * @return
	 */
	public static CommandLine parse(String[] cmdLineArgs) {
		cmdParser = new DefaultParser();
		helpFormatter = new HelpFormatter();
		
		Options options = createOptions();
		
		try {
			cmdLine = cmdParser.parse(options, cmdLineArgs);
			return cmdLine;
		} catch (ParseException ex) {
			printHelp(cmdLineArgs, ex);
			return null;
		}
	}

	/**
	 * Prints usage message if something went wrong during parsing.
	 * 
	 * @param commandLineArgs command line arguments
	 * @param e               Parse Exception
	 * 
	 */
	private static void printHelp(String[] commandLineArgs, ParseException ex) {
		helpFormatter.printHelp("ERROR: Unable to parse command-line arguments\n " + Arrays.toString(commandLineArgs)
				+ " due to: " + ex + "\nExample: java -jar sensorDataImporter -i 'path to your sensor data folder'",
				options);
		System.exit(1);
	}
}
