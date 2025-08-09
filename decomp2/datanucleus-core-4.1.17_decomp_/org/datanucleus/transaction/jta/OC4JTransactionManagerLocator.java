package org.datanucleus.transaction.jta;

import org.datanucleus.NucleusContext;

public class OC4JTransactionManagerLocator extends JNDIBasedTransactionManagerLocator {
   public OC4JTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   public String getJNDIName() {
      return "java:comp/pm/TransactionManager";
   }
}
