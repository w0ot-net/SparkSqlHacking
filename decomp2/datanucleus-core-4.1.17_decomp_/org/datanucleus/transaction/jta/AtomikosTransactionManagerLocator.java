package org.datanucleus.transaction.jta;

import javax.transaction.TransactionManager;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.util.NucleusLogger;

public class AtomikosTransactionManagerLocator implements TransactionManagerLocator {
   public AtomikosTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   public TransactionManager getTransactionManager(ClassLoaderResolver clr) {
      Class cls = clr.classForName("com.atomikos.icatch.jta.UserTransactionManager");

      try {
         return (TransactionManager)cls.newInstance();
      } catch (Exception e) {
         NucleusLogger.TRANSACTION.debug("Exception obtaining Atomikos transaction manager " + e.getMessage());
         return null;
      }
   }
}
