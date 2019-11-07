package ohdm.storage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EMFWrapper {
	private EntityManagerFactory factory;
	
	public EMFWrapper(EntityManagerFactory emf) {
		this.factory = emf;
	}
	
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
