CREATE TABLE sensor_data (songID INTEGER PRIMARY KEY, songTitle VARCHAR(100) NOT NULL, songArtist VARCHAR(100) NOT NULL, SongAlbum VARCHAR(100) NOT NULL, songReleased INTEGER NOT NULL);

CREATE TABLE User (userID VARCHAR(50) PRIMARY KEY, password VARCHAR(50) NOT NULL, firstName VARCHAR(50) NOT NULL ,lastName VARCHAR(50) NOT NULL);
