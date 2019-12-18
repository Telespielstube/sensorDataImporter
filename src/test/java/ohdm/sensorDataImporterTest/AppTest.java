package ohdm.sensorDataImporterTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ohdm.bean.Sensor;
import ohdm.sensorDataImporter.*;


public class AppTest {

    String zipFilePath;
    String extractTo;

    @Before
    public void setup() {
        zipFilePath = "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/zip";
        extractTo = "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/csv";
    }

    @Test
    public void testIfAllFilesInFolderGetRead() throws FileNotFoundException, IOException {
        Reader reader = new Reader();
        File[] files = reader.readFile(zipFilePath, ".zip");
        Assert.assertEquals(new File("/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/zip/2015-10-01_ppd42ns_sensor_27.zip"), files[0]);
        Assert.assertEquals(new File("/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/zip/2016-01_dht22.zip"), files[1]);      
    }

    @Test
    public void testIfZipFileGetsUnzipped() throws FileNotFoundException, IOException {
        File file = new File(zipFilePath);
        File[] files = file.listFiles();
        Unzip unzip = new Unzip();
        unzip.fileUnzip(files, extractTo);
        Assert.assertTrue(true);       
    }
    
    @Test 
    public void testIfNonExistingFilesReturnAnError() throws FileNotFoundException, IOException {
        Reader reader = new Reader();
        File[] files = reader.readFile(zipFilePath, ".csv");   
        Assert.assertTrue(files.length == 0);
    }
    
    @Test
    public void testIfCSVFileGetsParsed() throws FileNotFoundException, IOException {
        File file = new File(extractTo);
        File[] files = file.listFiles();
        Parser fileParser = new Parser();
        ArrayList<Sensor> parsedFile = fileParser.parseFile(files);
        Assert.assertTrue(parsedFile.toString(), true);
    }
    
    @After
    public void cleanUp() throws IOException {
        File file = new File(extractTo);
        FileUtils.cleanDirectory(file);
    } 
} 
