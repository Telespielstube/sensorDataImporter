package ohdm.storage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import ohdm.bean.SensorData;

public class SensorDataDbDao implements Dao<SensorData> {

	private EMFWrapper emf;

	public SensorDataDbDao() {
		this.emf = new EMFWrapper(Persistence
				.createEntityManagerFactory("postgis_ohdm"));
	}
	public SensorDataDbDao(EMFWrapper emf) {
		this.emf = emf;
	} 
	

	public void saveData(SensorData sensorData) {
		EntityManager em = emf.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			em.persist(sensorData);
			transaction.commit();
		} catch (PersistenceException e) {
			System.err.println("Could not persist sensor data." + e.toString());
		} finally {
			em.close();
		}
	}
}
