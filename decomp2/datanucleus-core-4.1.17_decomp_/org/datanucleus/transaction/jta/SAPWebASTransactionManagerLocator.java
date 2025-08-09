package org.datanucleus.transaction.jta;

import org.datanucleus.NucleusContext;

public class SAPWebASTransactionManagerLocator extends JNDIBasedTransactionManagerLocator {
   public SAPWebASTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   public String getJNDIName() {
      return "TransactionManager";
   }
}
