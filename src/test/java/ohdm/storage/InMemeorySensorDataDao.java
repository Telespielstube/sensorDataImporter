package ohdm.storage;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ohdm.bean.SensorData;

public class InMemeorySensorDataDao implements SensorDao {
	private Map<Integer,SensorData> storage;

    public void InMemorySensorDataDao() {
        storage = new ConcurrentHashMap<Integer,SensorData>();
        initSomeSongs();
    }

    private void initSomeSongs() {
        SensorData sensor1 = new SensorData(1, "DHT22", "2016-01-01", 21.60f, 53.29f);        
        storage.put(sensor1.getSensorId(), sensor1);
        SensorData sensor2 = new SensorData(2, "DHT22", "2016-01-01", 20.00f, 53.29f);        
        storage.put(sensor2.getSensorId(), sensor2);
        SensorData sensor3 = new SensorData(3, "DHT22", "2016-01-01", 19.00f, 53.29f);        
        storage.put(sensor3.getSensorId(), sensor3);

    }

    public void saveData(SensorData sensorData) {
    	sensorData.setSensorId((int)storage.keySet().stream().count() + 1);
        storage.put(sensorData.getSensorId(), sensorData);
    }
}
