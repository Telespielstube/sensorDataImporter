package ohdm.bean;

public class DataSample {
    private float value;
    private String name;
    
    public DataSample(String name, float value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public float getValue() {
        return value;
    }
    
    
}
