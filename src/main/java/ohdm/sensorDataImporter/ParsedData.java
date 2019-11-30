package ohdm.sensorDataImporter;

public class ParsedData {

    private int importedSensorId;
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
        this.importedSensorId = p.importedSensorId;
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
    public int getImportedSensorId() {
        return sensorId;
    }
    
    public void setImportedSensorId(int sensorId) {
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
    
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public float getValue1() {
        return value1;
    }
    
    public void setValue1(float value1) {
        this.value1 = value1;
    }
    
    public float getValue2() {
        return value2;
    }
    
    public void setValue2(float value2) {
        this.value2 = value2;
    }    
    public float getValue3() {
        return value3;
    }
    
    public void setValue3(float value3) {
        this.value3 = value3;
    }    
    public float getValue4() {
        return value4;
    }
    
    public void setValue4(float value4) {
        this.value4 = value4;
    }    
    public float getValue5() {
        return value5;
    }
    
    public void setValue5(float value5) {
        this.value5 = value5;
    }    
    public float getValue6() {
        return value6;
    }
    
    public void setValue6(float value6) {
        this.value6 = value6;
    }    

    public float getValue7() {
        return value7;
    }
    
    public void setValue7(float value7) {
        this.value7 = value7;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
}
