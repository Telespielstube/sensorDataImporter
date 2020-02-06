# SensorDataImporter 

# Table Of Contents
1. Overview
2. Installation
3. Invoking
3.1 sensorDataImporter.sh
3.1.1 Setup cron job
3.2 LuftdatenImporter.jar
4. Functionality
4.1 sensorDataDownloader.sh
4.2 LuftdatenImporter.jar



# 1. Overview

sensorDataImporter.jar and sensorDataDownloader.sh are utilities for downloading archived sensor data from the www.archive.luftdaten.info webpage. 
The features of the shell script are:
- file download from the above mentioned webpage.
- stores downloaded files in a folder.
- Incremental downloads 

Features of SensorDataImporter application:
- extracts downloaded files to a folder.
- parses theses files.
- inserts parsed data into ohdm database.

# 2. Installation

SensorDataImporter can be installed:
using GitHub: https://github.com/Telespielstube/sensorDataImporter.git

Building the Java application from source:
Go to your sensorDataImporter folder and type: 
`mvn clean package`
This cleans up previous build artifacts and creates an executable jar file in the `/target` subfolder. 


# 3. Invoking

## 3.1 sensorDataDownloader.sh

!! Important
In case you do not use the zsh shell you first need to change the first line from:
`!#/bin/zsh`
to:
`!#/bin/your preferred shell environment`

It is very simple to invoke the shell script. The basic syntax is:
`./sensorDataDownloader.sh`

The shell script uses the standard folder ~/Documents/archive.luftdaten.info/ for downloading the archive.
If you wish to choose your own download folder you need to add an argument like:
``./sensorDataDownloader.sh ~/Downloads/luftdaten``

For further informations or script adjustments edit the script.

### 3.1.1 Setup cron job
If you wish to execute the shell script on a regular basis(regularily), you need to setup a cron job first. Read more about cron jobs by typing at your shell prompt: 
`man cron` 
or go to https://crontab.guru and use the simple and easy to use web-editor to shedule your job. 

To open and edit/update your crontab file, type the following at your shell prompt:
`crontab -e`

This opens your prefered text editor (fx. vi, vim, nano), if you have not specified a cron job before, the file should be empty. Now, add your routine:

For example, if you would like to shedule a job for every week on monday at 10 a.m. the command would look like:
`0 10 * * 1 cd /User/marta && ./sensorDataDownloader.sh ~/Downloads/luftdaten` 

In short, the first fields means minute, hour, day of months, month, day of week followed by the command (in this example the shell script). 


## 3.2 LuftdatenImporter.jar
 
 To execute the Java application you need to add two arguments to the command line: 
 `-i` 
 	path to the folder where the archive got downloaded. 
 `-u`
 	path to folder where the extracted files should be stored. 

e.g.
`java -jar LuftdatenImporter-1.0.jar -i ~/Downloads/luftdaten -u ~/Documents/extractedLuftdaten`



# 4. Functionality   

## 4.1 Shell script
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
"	Does not create a hierarchy of directories when retrieving recursively. With this option turned on, all files 
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
	
	
4.2 LuftdatenImporter.jar
The application reads all .zip files from the specified path in the first argument and extracts all files to the path specified in the second argument.
Then parses all extracted .csv files based on their header length to a sensor object with its associated values. 

E.g. a csv file containing DHT data looks like this:
sensor_id;	sensor_type;	location;	lat;	lon;	timestamp;							temperature;	humidity
48;			DHT22;			19;			48.722;	9.209;	2016-01-01T00:00:05.737592+00:00;	21.5;			45.00

The first 5 attributes belong to the sensor object, the following attributes mark the measured data (number varies from sensor to sensor).

Supported and tested sensors are:
PPD42
DHT22
SHT31
HPM
HTU21d
DS180B20
BME280
BMP280
BMP180

