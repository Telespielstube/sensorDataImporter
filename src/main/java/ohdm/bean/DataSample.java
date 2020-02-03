package ohdm.bean;

public class DataSample {
    private float value;
    private String name;
    private String timestamp;
    
    public DataSample(String name, float value) {
        this.name = name;
        this.value = value;
    }
    
    public DataSample(String name, String timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }
    
    public String getName() {
        return this.name;
    }
    
    public float getValue() {
        return this.value;
    }
    
    public String getTimestamp() {
        return this.timestamp;
    }
}
