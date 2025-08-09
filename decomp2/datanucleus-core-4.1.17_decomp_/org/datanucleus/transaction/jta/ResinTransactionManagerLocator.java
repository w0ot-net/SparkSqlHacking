package org.datanucleus.transaction.jta;

import org.datanucleus.NucleusContext;

public class ResinTransactionManagerLocator extends JNDIBasedTransactionManagerLocator {
   public ResinTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   public String getJNDIName() {
      return "java:comp/TransactionManager";
   }
}
