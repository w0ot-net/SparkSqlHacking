package org.datanucleus.transaction.jta;

import org.datanucleus.NucleusContext;

public class WebLogicTransactionManagerLocator extends JNDIBasedTransactionManagerLocator {
   public WebLogicTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   public String getJNDIName() {
      return "javax.transaction.TransactionManager";
   }
}
