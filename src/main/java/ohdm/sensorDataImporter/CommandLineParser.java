package ohdm.sensorDataImporter;

import java.util.Arrays;

import org.apache.commons.cli.*;

public class CommandLineParser {
	private static Options options;
	private static DefaultParser cmdParser;
	private static HelpFormatter helpFormatter;
	private static CommandLine cmdLine;

	/** Definition of all available command line arguments.
	 * 
	 * @return command line arguments.
	 */
	private static Options createOptions() {
		Option pathOption1 = Option.builder("i").required().hasArg().desc("path to directory.").build();
		Option pathOption2 = Option.builder("u").required().hasArg().desc("path where to unzip files.").build();
		options = new Options();
		options.addOption(pathOption1);
		options.addOption(pathOption2);

		return options;
	}

	/** Parsing command line arguments.
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

	/** Prints usage message if something went wrong during parsing.
	 * 
	 * @param commandLineArgs command line arguments
	 * @param e               Parse Exception
	 * 
	 */
	private static void printHelp(String[] commandLineArgs, ParseException ex) {
		helpFormatter.printHelp("ERROR: Unable to parse command-line arguments\n " + Arrays.toString(commandLineArgs)
				+ " due to: " + ex + "\nExample: java -jar sensorDataImporter.jar -i 'path to your sensor data folder' -u 'path where to unzip files",
				options);
		System.exit(1);
	}
}
