package org.datanucleus.transaction.jta;

import org.datanucleus.NucleusContext;

public class SunTransactionManagerLocator extends JNDIBasedTransactionManagerLocator {
   public SunTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   public String getJNDIName() {
      return "java:appserver/TransactionManager";
   }
}
