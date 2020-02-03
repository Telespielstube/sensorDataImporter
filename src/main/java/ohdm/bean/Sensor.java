package ohdm.bean;

import java.util.ArrayList;

public class Sensor {
    private int importedSensorId;
    private String sensorType;
    private int location;
    private float latitude;
    private float longitude;
   // private String timestamp;
    private ArrayList<DataSample> samples;

    public Sensor(int importedSensorId, String sensorType, int location, float latitude, float longitude /*, String timestamp */) {
        this.importedSensorId = importedSensorId;
        this.sensorType = sensorType;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
     //   this.timestamp = timestamp;
        this.samples = new ArrayList<DataSample>();
    }
        
    // Getters and setters
    public int getImportedSensorId() {
        return importedSensorId;
    }

    public void setImportedSensorId(int importedSensorId) {
        this.importedSensorId = importedSensorId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }
    
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
/*
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    */
    public void addDataSample(DataSample sample) {
        samples.add(sample);
    }
    
    public DataSample getDataSample(int index) throws ArrayIndexOutOfBoundsException {
        return samples.get(index);
    }
    
}