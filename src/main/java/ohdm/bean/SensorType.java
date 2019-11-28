package ohdm.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "sensor_data")
public class SensorType {

	private int id;
	private String type;
	
	@OneToMany(mappedBy = "sensor_type", cascade=CascadeType.ALL)
	Set<TemperatureData> temperatureData;
	
	public SensorType() { }
	
	public SensorType(int id, String type) {
		this.id = id;
		this.type = type;
	}
	
	public SensorType(SensorType s) {
	    this.id = s.id;
	    this.type = s.type;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "sensor_id")
	public int getSensorId() {
		return id;
	}
	
	public void setSensorId(int sensorId) {
		this.id = sensorId;
	}
	
	@Column(name= "sensor_type")
	public String getSensorType() {
		return type;
	}
	
	public void setSensorType(String sensorType) {
		this.type = sensorType;
	}
}
