package org.apache.derby.impl.store.access;

import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.shared.common.error.StandardException;

final class RAMTransactionContext extends ContextImpl {
   private RAMTransaction transaction;
   private final boolean abortAll;

   public void cleanupOnError(Throwable var1) throws StandardException {
      boolean var2 = false;
      if (!this.abortAll && var1 instanceof StandardException var3) {
         if (var3.getSeverity() < 30000) {
            return;
         }

         if (var3.getSeverity() >= 40000) {
            var2 = true;
         }
      } else {
         var2 = true;
      }

      if (this.transaction != null) {
         try {
            this.transaction.invalidateConglomerateCache();
         } catch (StandardException var4) {
         }

         this.transaction.closeControllers(true);
      }

      if (var2) {
         this.transaction = null;
         this.popMe();
      }

   }

   RAMTransactionContext(ContextManager var1, String var2, RAMTransaction var3, boolean var4) throws StandardException {
      super(var1, var2);
      this.abortAll = var4;
      this.transaction = var3;
      this.transaction.setContext(this);
   }

   RAMTransaction getTransaction() {
      return this.transaction;
   }

   void setTransaction(RAMTransaction var1) {
      this.transaction = var1;
   }
}
