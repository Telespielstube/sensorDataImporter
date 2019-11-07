package ohdm.storage;

import ohdm.bean.SensorData;

public interface SensorDataDao {

	/** Adds a new row of measures values.
	 * 
	 * @param sensorData	measured data of sensor.
	 */
	public void addNewSensorData(SensorData sensorData);
}
