package ohdm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="fine_dust_data")
public class FineDustData {
    private int id;
    private float pm10; //particulate matter 10 μm
    private float durationPm10;
    private float ratioPm10;
    private float pm25; // particulate matter 2.5 μm
    private float durationPm25;
    private float ratioPm25;
    private float pm0; // ultra fine dust particulate matter <0,1 μm 
    
    @ManyToOne
    @JoinColumn(name="sensor_id")
    SensorType sensorType;
    
    public FineDustData() {}
    
    public FineDustData(int id, float pm10, float durPm10, float ratioPm10, float pm25, float durPm25, float ratioPm25, float pm0) {
        this.id = id;
        this.pm10 = pm10;
        this.durationPm10 = durPm10;
        this.ratioPm10 = ratioPm10;
        this.pm25 = pm25;
        this.durationPm25 = durPm25;
        this.ratioPm25 = ratioPm25;
        this.pm0 = pm0;
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "fine_dust_id")
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "P1")
    public float getPm10() {
        return pm10;
    }
    
    public void setPm10(float pm10) {
        this.pm10 = pm10;
    }
    
    @Column(name = "durP1")
    public float getDurationPm10() {
        return durationPm10;
    }
    
    public void setDurationPm10(float durPm10) {
        this.durationPm10 = durPm10;
    }
    
    @Column(name = "ratioP1")
    public float getRatioPm10() {
        return ratioPm10;
    }
    
    public void setRatioPm10(float ratioPm10) {
        this.ratioPm10 = ratioPm10;
    }
    @Column(name = "P2")
    public float getPm25() {
        return pm25;
    }
    
    public void setPm25(float pm25) {
        this.pm25 = pm25;
    }
    
    @Column(name = "durP2")
    public float getDurPm25() {
        return durationPm25;
    }
    
    public void setDurPm25(float durPm25) {
        this.durationPm25 = durPm25;
    }
    
    @Column(name = "ratioP2")
    public float getRatioPm25() {
        return ratioPm25;
    }
    
    public void setRatioPm25(float ratioPm25) {
        this.ratioPm25 = ratioPm25;
    }
    
    @Column(name = "P0")
    public float getPm0() {
        return pm0;
    }
    
    public void setPm0(float pm0) {
        this.pm0 = pm0;
    }
}
