package org.datanucleus.transaction.jta;

import org.datanucleus.NucleusContext;

public class JBossTransactionManagerLocator extends JNDIBasedTransactionManagerLocator {
   public JBossTransactionManagerLocator(NucleusContext nucleusCtx) {
   }

   public String getJNDIName() {
      return "java:/TransactionManager";
   }
}
