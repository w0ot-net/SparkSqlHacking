package org.datanucleus.transaction.jta;

import javax.transaction.TransactionManager;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.util.NucleusLogger;

public abstract class FactoryBasedTransactionManagerLocator implements TransactionManagerLocator {
   protected abstract Class getFactoryClass(ClassLoaderResolver var1);

   public TransactionManager getTransactionManager(ClassLoaderResolver clr) {
      Class factoryClass = this.getFactoryClass(clr);
      if (factoryClass == null) {
         return null;
      } else {
         try {
            return (TransactionManager)factoryClass.getMethod("getTransactionManager").invoke((Object)null);
         } catch (Exception e) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug("Exception finding FactoryBased transaction manager " + e.getMessage());
            }

            return null;
         }
      }
   }
}
