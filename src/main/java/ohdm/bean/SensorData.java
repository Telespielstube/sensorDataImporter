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
	private float temperature;
	private float humidity;
	
	public SensorData() { }
	
	public SensorData(int id, float temperature, float humidity) {
		this.id = id;
		this.temperature = temperature;
		this.humidity = humidity;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	
	@Column(name = "temperature")
	public float getTemperature() {
		return temperature;
	}
	
	@Column(name= "humidity")
	public float getHumidity() {
		return humidity;
	}
	
}
