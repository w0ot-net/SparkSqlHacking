package javax.jdo;

import javax.persistence.EntityManagerFactory;

public interface JDOEntityManagerFactory extends EntityManagerFactory, PersistenceManagerFactory {
   JDOEntityManager getPersistenceManager();

   JDOEntityManager getPersistenceManagerProxy();

   JDOEntityManager getPersistenceManager(String var1, String var2);
}
