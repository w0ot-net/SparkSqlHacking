package org.datanucleus.transaction.jta;

import javax.naming.InitialContext;
import javax.transaction.Synchronization;
import javax.transaction.TransactionSynchronizationRegistry;

public class JTASyncRegistry {
   TransactionSynchronizationRegistry registry;

   public JTASyncRegistry() throws JTASyncRegistryUnavailableException {
      try {
         InitialContext ctx = new InitialContext();
         this.registry = (TransactionSynchronizationRegistry)ctx.lookup("java:comp/TransactionSynchronizationRegistry");
      } catch (Throwable var2) {
         throw new JTASyncRegistryUnavailableException();
      }
   }

   public void register(Synchronization sync) {
      this.registry.registerInterposedSynchronization(sync);
   }
}
