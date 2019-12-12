package ohdm.sensorDataImporterTest;

import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

import ohdm.bean.SensorType;
import ohdm.sensorDataImporter.*;

public class AppTest {

    String zipFilePath;
    String extractTo;

    @Before
    public void setup() {
        zipFilePath = "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/";
        extractTo = "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/extracted";
    }

    @Test
    public void testIfAllFilesInFolderGetRead() throws FileNotFoundException, IOException {
        Reader reader = new Reader();
        File[] files = reader.readFile(zipFilePath, ".zip");
        Assert.assertEquals(new File("/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/2016-01_dht22.zip"), files[0]);
        Assert.assertEquals(new File("/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/2015-11_ppd42ns.zip"), files[1]);
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
        File[] files = reader.readFile(extractTo, ".csv");   
        Assert.assertTrue(files.length == 0);
    }
    
    @Test
    public void testIfCSVFileGetsParsed() throws FileNotFoundException, IOException {
        File file = new File(extractTo);
        File[] files = file.listFiles();
        Parser fileParser = new Parser();
        ArrayList<ParsedData> parsedFile = fileParser.parseFile(files);
        Assert.assertTrue(parsedFile.toString(), true);
    }
    
    @After
    public void cleanUp() throws IOException {
        File file = new File(extractTo);
        FileUtils.cleanDirectory(file);
    } 
} 
