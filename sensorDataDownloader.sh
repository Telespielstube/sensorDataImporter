#!/bin/zsh
# This script downloads all .zip files from "https://archive.luftdaten.info/csv_per_month/".
#`
# The script creates a folder named "archive.luftdaten.info" in your personal Documents
# folder. (/users/your user name/Documents).
# 
# For further informations about the downloaded data read your wget.log file. There you can
# 
# Default values are shown, uncomment to change 

#Download url
URL=https://archive.luftdaten.info/csv_per_month/

#Directory where the files get downloaded
DIR=~/Documents/

# output on terminal
echo "Downloading files from archive.luftdaten.info"

#change directory
#cd \$DIR

#wget command
#If you only need to download specific files from the url add -A "your Pattern" to the options. (eg. -A "*dht22*" for downloading dht22 files only.)
wget -nd -r -np -P $DIR/archive.luftdaten.info/ -R "index.html*" --cut-dirs=2 $URL

echo "Congratiulation!! You successfully downloaded the complete archive to: " $DIR/"archive.luftdate.info"
