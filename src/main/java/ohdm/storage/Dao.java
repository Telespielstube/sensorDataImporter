package ohdm.storage;


public interface Dao<T> {

	/** Adds a new row of measures values.
	 * 
	 * @param sensorData	measured data of sensor.
	 */
	public void saveData(T t);
}
