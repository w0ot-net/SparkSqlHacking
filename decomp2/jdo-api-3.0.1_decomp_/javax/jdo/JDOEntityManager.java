package javax.jdo;

import javax.persistence.EntityManager;

public interface JDOEntityManager extends EntityManager, PersistenceManager {
   JDOEntityManagerFactory getPersistenceManagerFactory();
}
