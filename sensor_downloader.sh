#!/bin/zsh
# This script downloads all .zip files from "https://archive.luftdaten.info/csv_per_month/".
#
# The script creates a folder named "archive.luftdaten.info" in your personal Documents
# folder. (/users/your user name/Documents).
# 
# For further informations about the downloaded data read your wget.log file.
# 
# Default values are shown, uncomment to change 

#Download url
URL=https://archive.luftdaten.info/csv_per_month/

# Output on terminal
echo "Downloading files from archive.luftdaten.info"

# If first variable is null all files get downloaded to the default path else files get downloaded to user specified path.
if [ -z "$1" ]
then
	#Directory where the files get downloaded.  
	DIR=~/Documents/archive.luftdaten.info/
else
	# First argument gets assigned to DIR variable.
	DIR=$1
fi	
#wget command
#If you only need to download specific files from the url add -A "your Pattern" to the options. (eg. -A "*dht22*" for downloading files containing dht22 substring.)
wget -c -nd -r -np -P $DIR -R "index.html*" --cut-dirs=2 $URL

# Finished downloading complete archive.
echo "Congratulation!! You successfully downloaded the complete archive"
