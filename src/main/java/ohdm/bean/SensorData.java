package ohdm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "sensor_data")
public class SensorData {

	private int id;
	private String type;
	private String timestamp;
	private float temperature;
	private float humidity;
	
	public SensorData() { }
	
	public SensorData(int id, String type, String timestamp, float temperature, float humidity) {
		this.id = id;
		this.type = type;
		this.timestamp = timestamp;
		this.temperature = temperature;
		this.humidity = humidity;
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

	@Column(name= "timestamp")
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@Column(name = "temperature")
	public float getTemperature() {
		return temperature;
	}
	
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	@Column(name= "humidity")
	public float getHumidity() {
		return humidity;
	}
	
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
}
