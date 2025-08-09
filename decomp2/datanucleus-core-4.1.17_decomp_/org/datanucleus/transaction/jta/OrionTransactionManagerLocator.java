package org.datanucleus.transaction.jta;

import org.datanucleus.NucleusContext;

public class OrionTransactionManagerLocator extends JNDIBasedTransactionManagerLocator {
   public OrionTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   public String getJNDIName() {
      return "java:comp/UserTransaction";
   }
}
