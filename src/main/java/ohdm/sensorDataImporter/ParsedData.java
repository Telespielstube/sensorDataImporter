package ohdm.sensorDataImporter;

public class ParsedData {

    private int sensorId;
    private String sensorType;
    private int location;
    private float latitude;
    private float longitude;
    private String timestamp;
    private float value1;
    private float value2;
    private float value3;
    private float value4;
    private float value5;
    private float value6;
    private float value7;
    
    public ParsedData() { }
    
    public ParsedData(ParsedData p) {
        this.sensorId = p.sensorId;
        this.sensorType = p.sensorType;
        this.location = p.location;
        this.latitude = p.latitude;
        this.longitude = p.longitude;
        this.timestamp = p.timestamp;
        this.value1 = p.value1;
        this.value2 = p.value2;
        this.value3 = p.value3;
        this.value4 = p.value4;
        this.value5 = p.value5;
        this.value6 = p.value6;
        this.value7 = p.value7;
    }
    public int getSensorId() {
        return sensorId;
    }
    
    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
