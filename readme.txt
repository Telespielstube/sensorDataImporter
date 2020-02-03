SensorDataImporter 

Table Of Contents
1. Overview
2. Invoking
3. Functionality



1. Overview

SensorDataImporter.jar and sensorDataDownloader.sh are utilities for downloading archived sensor data from the www.archive.luftdaten.info webpage. 
The features of the shell script are:
- file download from the above mentioned webpage.
- stores downloaded files in a folder.

Features of SensorDataImporter application:
- extracts downloaded files to a folder.
- parses theses files.
- inserts parsed data into ohdm database.



2. Invoking

sensorDataDownloader.sh

!! Important
In case you do not use the zsh shell you first need to change the first line from:
!#/bin/zsh
to:
!#/bin/your preferred shell environment

It is very simple to invoke the shell script. The basic syntax is:
./sensorDataDownloader.sh

The shell script uses the standard folder ~/Documents/archive.luftdaten.info/ for downloading the archive.
If you wish to choose your own download folder you need to add an argument like:
./sensorDataDownloader.sh ~/Downloads/luftdaten

For further informations or script adjustments edit the script.

 SensorDataImporter.jar
 
 To execute the Java application you need to add two arguments to the command line: 
 -i 
 	path to the folder where the archive got downloaded. 
 -u
 	path to folder where the extracted files should be stored. 

 e.g.
 java -jar SensorDataImporter.jar -i /Users/marta/Downloads/luftdaten -u /Users/marta/Documents/extractedLuftdaten
 
3. Functionality   

sensorDataDownloader.sh
The script uses the free-utility wget for file download:

The URL to the Luftdaten archive is assigned to the variable URL 
URL=https://archive.luftdaten.info/csv_per_month/

The path to the download folder is assigned to the DIR variable:
DIR=~/Documents/archive.luftdaten.info/

This makes the wget comand more readable
wget -A "*dht22*" -c -nd -r -np -P $DIR -R "index.html*" --cut-dirs=2 $URL

Used options in the wget command:
-A --accept	
	Specify comma-sperated lists of file name suffixes or patterns to accept. E.g. a pattern like "*dht22*" 

-c --continue
	In case the connection gets lost download, wget can resume downloading where it stopped before the interruption.
	!!! pretty useful for large amount of data.!!!

-nd --no-directory
	Does not create a hierarchy of directories when retrieving recursively. With this option turned on, all files 
	will get saved to the current directory

-r --recursive
	turn on recursive download.
	
-np --no-parent
	Do not ever ascend to the parent directory when retrieving recursively. This option is a useful option, 
	since it guarantees that only the files below a certain hierarchy will be downloaded.

-P --directory-prefix
	To save the file in a different location. Like the $DIR variable.

-R --reject
	Specify comma-sperated lists of file name suffixes or patterns to reject. E.g. a pattern like "index.html*"

--cut-dirs=2
	Ignore number directory components. This option is useful for getting a fine-grained control over 
	the directory where recursive retrieval will be saved.

For more information about wget go to: https://www.gnu.org/software/wget/manual/wget.html#Overview 	
	
SensorDataDownloader.jar
The application reads all .zip files from the specified path in the first argument and extracts all files to the path specified in 
the second argument.
Then parses all extracted .csv files based on their header length to a sensor object with its associated values. 
E.g. a csv file containing DHT data looks like this:
sensor_id;	sensor_type;	location;	lat;	lon;	timestamp;							temperature;	humidity
48;			DHT22;			19;			48.722;	9.209;	2016-01-01T00:00:05.737592+00:00;	21.5;			45.00

The application parses the first 6 columns to a sensor object and the rest as its values.
E.g. Each sensor attribute represents a column and the last attribute is referencing the associated column name and value:
public class Sensor {
	private int importedSensorId;
	private String sensorType;
	private int location;
	private float latitude;
	private float longitude;
	private String timestamp;
	private ArrayList<DataSample> samples;
	
	// Constructor, getters and setters
}
public class DataSample {
    private float value;
    private String name;
    
    // Constructor, getters and setters
}


