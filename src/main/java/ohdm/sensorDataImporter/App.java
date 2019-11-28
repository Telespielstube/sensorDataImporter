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
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.cli.CommandLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import ohdm.bean.SensorType;
import ohdm.sensorDataImporter.*;
import ohdm.storage.DBConnection;
import ohdm.storage.DBSensorData;

public class App 
{
	
	//private static String extractTo = "C:\\test";
	private static String extractTo = "/Users/marta/extractedLuftdaten";
    
    public static void main( String[] args ) throws FileNotFoundException, IOException 
    
    {
    	ArrayList<ParsedData> dataList = new ArrayList<>();
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
        dataList = fileParser.parseFile(listOfFiles);
        for (int i = 0; i < dataList.size(); ++i) {
            System.out.println(dataList.get(i).getSensorId() +  " " + dataList.get(i).getSensorType());
            
        }
        
    	//   Add Sensordata
    	//DBConnection db = new DBConnection("jdbc:postgresql://localhost:5432/postgis_ohdm", "postgres","OHDM4ever!");
    	DBConnection db = new DBConnection("jdbc:postgresql://localhost:5432/postgis_ohdm", "marta","0000");
    	DBSensorData sensordata = new DBSensorData(db);
    	
//    	try {
//    		for (int i = 0; i < sensorList.size(); i++) {
//    			sensordata.addNewSensorData(sensorList.get(i));
//			}
//    		
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
}
