package ohdm.storage;

import java.util.Optional;


public interface Dao<T> {

	/** Adds a new row of measures values.
	 * 
	 * @param sensorData	measured data of sensor.
	 */
	public void saveData(T t);
	
	public Optional<T> getData(int id);
}
