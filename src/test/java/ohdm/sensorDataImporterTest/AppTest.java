package ohdm.sensorDataImporterTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import ohdm.bean.Sensor;
import ohdm.sensorDataImporter.*;

public class AppTest {

    String zipFiles = "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/zip";
    String extractTo = "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/extracted";
    String csvFile = "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/csv";

    @Test
    public void testIfAllFilesInFolderGetRead() throws FileNotFoundException, IOException {
        Reader reader = new Reader();
        File[] files = reader.readFile(zipFiles, ".zip");
        Assert.assertEquals(new File(
                "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/zip/2015-10-01_ppd42ns_sensor_27.zip"),
                files[0]);
        Assert.assertEquals(new File(
                "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/zip/2019-11-26_dht22_sensor_1015.zip"),
                files[1]);
        Assert.assertEquals(new File(
                "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/zip/2016-10-10_sds011_sensor_183.zip"),
                files[2]);
        Assert.assertEquals(new File(
                "/Users/marta/Documents/eclipse-workspace/sensorDataImporter/src/test/resources/zip/2016-10-10_ppd42ns_sensor_49.zip"),
                files[3]);
    }

    @Test
    public void testIfZipFileGetsUnzipped() throws FileNotFoundException, IOException {
        File file = new File(zipFiles);
        File[] files = file.listFiles();
        Unzip unzip = new Unzip();
        unzip.fileUnzip(files, extractTo);
        Assert.assertTrue(files.length > 0);
    }
    

    @Test
    public void testIfNonExistingFilesReturnAnError() throws FileNotFoundException, IOException {
        Reader reader = new Reader();
        File[] files = reader.readFile(zipFiles, ".csv");
        Assert.assertTrue(files.length == 0);
    }

    @Test
    public void testIfCSVFileGetsParsed() throws FileNotFoundException, IOException {
        File file = new File(csvFile);
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
