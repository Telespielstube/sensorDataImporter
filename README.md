# SensorDataImporter 

# Table Of Contents
1. Overview
2. Installation\
2.1 Building the Java application from source
3. Invoking\
3.1 sensorDataImporter.sh\
3.1.1 Seting up the cron job\
3.2 LuftdatenImporter.jar
4. Functionality\
4.1 sensorDataDownloader.sh\
4.2 LuftdatenImporter.jar\
4.2.1 Parser\
4.2.2 Database Manager


# 1. Overview

sensorDataImporter.jar and sensorDataDownloader.sh are utilities for downloading archived sensor data from the www.archive.luftdaten.info webpage. 
The features of the shell script are:
* file download from the above mentioned webpage.
* storage of downloaded files in a folder.
* Incremental downloads 

Features of the LuftdatenImporter application are:
* extracts downloaded files to a folder.
* parses theses files.
* inserts parsed data into ohdm database.

# 2. Installation

SensorDataImporter can be built from sources using the repository on GitHub: https://github.com/Telespielstube/sensorDataImporter.git

## 2.1 Building the Java application from source
Go to your sensorDataImporter folder and type: 
`mvn clean package`
This cleans up previous build artifacts and creates an executable jar file in the `/target` subfolder. 

# 3. Invoking
## 3.1 sensorDataDownloader.sh
##### !! Important
In case you do not use the zsh shell you first need to change the first line from:
`!#/bin/zsh`
to:
`!#/bin/your preferred shell environment`
It is very simple to invoke the shell script. The basic syntax is:
`./sensorDataDownloader.sh`
The shell script uses the standard folder ~/Documents/archive.luftdaten.info/ for downloading the archive.
If you wish to choose your own download folder you need to add an argument like:
``./sensorDataDownloader.sh ~/Downloads/luftdaten``

For further information or script modifications edit the script.

### 3.1.1 Setting the up cron job
If you wish to execute the shell script periodically, you need to setup a cron job first. Read more about cron jobs by typing at your shell prompt: 
`man cron` 
or go to [crontab.guru](https://crontab.guru) and use the simple and easy to use web-editor to shedule your job. 
To open and edit/update your crontab file, type the following at your shell prompt:
`crontab -e`
This opens your prefered text editor (fx. vi, vim, nano), if you have not specified a cron job before, the file should be empty. Now, add your routine:

For example, if you would like to shedule a job for every week on monday at 10 a.m. the command would look like:\
`0 10 * * 1 cd /User/marta && ./sensorDataDownloader.sh ~/Downloads/luftdaten` 

In short, the first fields means minute, hour, day of months, month, day of week followed by the command (in this example, changing into a folder and then running the shell script). 

## 3.2 LuftdatenImporter.jar
 To execute the Java application you need to add two paramteres from the command line: 
 `-i` 
 	path to the folder where the archive got downloaded. 
 `-u`
 	path to folder where the extracted files should be stored. 
e.g.
`java -jar LuftdatenImporter-1.0.jar -i ~/Downloads/luftdaten -u ~/Documents/extractedLuftdaten`

# 4. Functionality   
## 4.1 sensorDataDownloader.sh
The script uses the free-utility wget for file download:

The URL to the Luftdaten archive is assigned to the variable URL 
`URL=https://archive.luftdaten.info/csv_per_month/`

The path to the download folder is assigned to the DIR variable:
`DIR=~/Documents/archive.luftdaten.info/~`

This makes the wget comand more readable
`wget -A "*dht22*" -c -nd -r -np -P $DIR -R "index.html*" --cut-dirs=2 $URL`

Used options in the wget command:
`-A --accept`
	Specify comma-separated lists of file name suffixes or patterns to accept. E.g. a pattern like "*dht22*" 

`-c --continue`
	In case the connection gets lost during download, wget can resume downloading where it stopped before the interruption.
	!!! pretty useful for large amount of data.!!!
	
`-nd --no-directory`
	Does not create a hierarchy of directories when retrieving recursively. With this option turned on, all files will get saved to the current directory

`-r --recursive`
	turn on recursive download.
	
`-np --no-parent`
	Do not ever ascend to the parent directory when retrieving recursively. This option is a useful option, since it guarantees that only the files below a certain hierarchy will be downloaded.

`-P --directory-prefix`
	To save the file in a different location. Like the $DIR variable.

`-R --reject`
	Specify comma-sperated lists of file name suffixes or patterns to reject. E.g. a pattern like "index.html*"

`--cut-dirs=2`
	Ignore number directory components. This option is useful for getting a fine-grained control over the directory where recursive retrieval will be saved.

For more information about wget go to: [GNU wget manual](https://www.gnu.org/software/wget/manual/wget.html#Overview) or type at your shell prompt:
`man wget`

## 4.2 LuftdatenImporter.jar
### 4.2.1 Parser
The application reads all .zip files from the specified path in the first argument and extracts all files to the path specified in the second argument.
Then, it parses all extracted .csv files based on their header length to sensor objects with its associated values. 

### 4.2.2 Database Manager
The method `createTables()` in the database manager class creates the needed tables to insert sensor data to ohm. The following tables are added:
* air_pressure
* fine_dust_data
* temperature_data
* imported_sensor

The method `insertSensorIntoDatabase()` adds the following entries for all sensors:
* a username and an id into the external_user table.
* an system name and a description of the data source. E.g. luftdaten and archive.luftdaten.info

Next it checks every sensor_type field if it contains a known sensor and calls the corresponding method for inserting the sensor data into the ohdm database. The method inserts the following sensor related data to the corresponding tables:
* Sensor classification like temperature, fine dust ...
* Geo Object entry which stores the sensor type and a reference to the user
* Points that indicate the location of the sensor. The method to convert from latitude and longitude in the .csv file to a geometry object uses the WKT language.
* ImportedSensor entry connects the .csv sensor id to the ohdm database id.
* The entry in the geoobject_geometry references all previous entries like userId, sensorId, pointsId, geoobjectId and how long the sensor is valid based on the location. 
* the meassurement of the sensor to the corresponding table.
* Inserts the converted .csv string date to a postgres timestamp data type.
* References the geoobject id in the geoobject table.

Supported and tested sensors are:
* PPD42
* DHT22
* SHT31
* HPM
* HTU21d
* DS180B20
* BME280
* BMP280
* BMP180

Currently not supported:
* PMS series
