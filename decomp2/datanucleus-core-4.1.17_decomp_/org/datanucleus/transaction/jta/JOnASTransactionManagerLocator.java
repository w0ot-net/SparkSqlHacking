package org.datanucleus.transaction.jta;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.util.NucleusLogger;

public class JOnASTransactionManagerLocator extends FactoryBasedTransactionManagerLocator {
   Class factoryClass = null;

   public JOnASTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   protected Class getFactoryClass(ClassLoaderResolver clr) {
      if (this.factoryClass != null) {
         return this.factoryClass;
      } else {
         try {
            try {
               this.factoryClass = clr.classForName("org.objectweb.jonas_tm.Current");
            } catch (Exception var3) {
            }
         } catch (Exception e) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug("Exception finding JOnAS transaction manager. Probably not in a JOnAS environment " + e.getMessage());
            }
         }

         return this.factoryClass;
      }
   }
}
