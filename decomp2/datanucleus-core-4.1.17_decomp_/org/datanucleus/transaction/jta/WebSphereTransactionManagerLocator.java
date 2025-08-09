package org.datanucleus.transaction.jta;

import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.NucleusContext;
import org.datanucleus.util.NucleusLogger;

public class WebSphereTransactionManagerLocator extends FactoryBasedTransactionManagerLocator {
   Class factoryClass = null;

   public WebSphereTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   protected Class getFactoryClass(ClassLoaderResolver clr) {
      if (this.factoryClass != null) {
         return this.factoryClass;
      } else {
         try {
            try {
               this.factoryClass = clr.classForName("com.ibm.ws.Transaction.TransactionManagerFactory");
            } catch (Exception var5) {
               try {
                  this.factoryClass = clr.classForName("com.ibm.ejs.jts.jta.TransactionManagerFactory");
               } catch (Exception var4) {
                  this.factoryClass = clr.classForName("com.ibm.ejs.jts.jta.JTSXA");
               }
            }
         } catch (Exception e) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug("Exception finding Websphere transaction manager. Probably not in a Websphere environment " + e.getMessage());
            }
         }

         return this.factoryClass;
      }
   }
}
