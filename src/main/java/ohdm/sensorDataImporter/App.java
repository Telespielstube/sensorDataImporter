package ohdm.sensorDataImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.cli.CommandLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import ohdm.bean.SensorData;
import ohdm.parser.*;
import ohdm.storage.DBConnection;
import ohdm.storage.DBSensorData;

public class App 
{
	
	private static String extractTo = "/home/martina/extractedLuftdatenArchive";
	
    public static void main( String[] args ) throws FileNotFoundException, IOException 
    
    {
    	int	rowAffected;
       	Unzip unzip = new Unzip();
    	Reader fileReader = new Reader();
    	Parser fileParser = new Parser();
    	File[] listOfFiles = null;
    	
    	CommandLine cmdLine = CommandLineParser.parse(args);
    	String path = cmdLine.getOptionValue("i");
    	
		listOfFiles = fileReader.readFile(path);
        unzip.fileUnzip(listOfFiles, extractTo);
        listOfFiles = fileReader.readFile(extractTo);
        fileParser.parseFile(listOfFiles);
    	
    	
//    	//   Add Sensordata
//    	DBConnection db = new DBConnection("jdbc:postgresql://localhost:5432/postgis_ohdm", "postgres","OHDM4ever!");
//    	DBSensorData sensordata = new DBSensorData(db);
//    	
//    	try {
//			System.out.println(sensordata.addNewSensorData(new SensorData(1,3.6f,2.5f)));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
}
