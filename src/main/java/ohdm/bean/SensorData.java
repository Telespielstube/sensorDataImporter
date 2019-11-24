package ohdm.bean;

import java.util.Date;

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
	private int location;
	private float latitude;
	private float longitude;
	private Date timestamp;
	private float temperature;
	private float humidity;
	
	public SensorData() { }
	
	public SensorData(int id, String type, int location, float latitude, float longitude, Date timestamp, float temperature, float humidity) {
		this.id = id;
		this.type = type;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
		this.temperature = temperature;
		this.humidity = humidity;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "sensor_id")
	public Integer getSensorId() {
		return id;
	}
	
	@Column(name= "sensor_type")
	public String getSensorType() {
		return type;
	}
	
	public void setSensorType(String sensorType) {
		this.type = sensorType;
	}
	
	@Column(name= "location")
	public int getLocation() {
		return location;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	@Column(name= "latitude")
	public float getLatitude() {
		return latitude;
	}
	
	public void setSensorType(float latitude) {
		this.latitude = latitude;
	}
	
	@Column(name= "timestamp")
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
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
