package org.datanucleus.transaction.jta;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.util.NucleusLogger;

public class BTMTransactionManagerLocator extends FactoryBasedTransactionManagerLocator {
   Class factoryClass = null;

   public BTMTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   protected Class getFactoryClass(ClassLoaderResolver clr) {
      if (this.factoryClass != null) {
         return this.factoryClass;
      } else {
         try {
            try {
               this.factoryClass = clr.classForName("bitronix.tm.TransactionManagerServices");
            } catch (Exception var3) {
            }
         } catch (Exception e) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug("Exception finding BTM transaction manager. Probably not in a BTM environment " + e.getMessage());
            }
         }

         return this.factoryClass;
      }
   }
}
