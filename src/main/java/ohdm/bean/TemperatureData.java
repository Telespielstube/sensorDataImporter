package ohdm.bean;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="temperature_data")
public class TemperatureData {
    private int id;
    private float temperature;
    private float humidity;
    
    @ManyToOne
    @JoinColumn(name="sensor_id")
    SensorType sensorType;
    
    public TemperatureData() {}
    
    public TemperatureData(int id, float temperature, float humidity) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "temperature_id")
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "temperature")
    public float getTemperature() {
        return temperature;
    }
    
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    
    @Column(name = "humidity")
    public float getHumidity() {
        return humidity;
    }
    
    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
