package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.store.raw.xact.RawTransaction;
import org.apache.derby.shared.common.error.StandardException;

final class XactContext extends ContextImpl {
   private RawTransaction xact;
   private RawStoreFactory factory;
   private boolean abortAll;

   XactContext(ContextManager var1, String var2, Xact var3, boolean var4, RawStoreFactory var5) {
      super(var1, var2);
      this.xact = var3;
      this.abortAll = var4;
      this.factory = var5;
      var3.xc = this;
   }

   public void cleanupOnError(Throwable var1) throws StandardException {
      boolean var2 = false;
      if (var1 instanceof StandardException var3) {
         if (this.abortAll) {
            if (var3.getSeverity() < 30000) {
               throw StandardException.newException("40XT5", var1, new Object[0]);
            }

            var2 = true;
         } else {
            if (var3.getSeverity() < 30000) {
               return;
            }

            if (var3.getSeverity() >= 40000) {
               var2 = true;
            }
         }
      } else {
         var2 = true;
      }

      try {
         if (this.xact != null) {
            this.xact.abort();
         }
      } catch (StandardException var7) {
         var2 = true;
         if (var7.getSeverity() <= 40000 && var7.getSeverity() >= ((StandardException)var1).getSeverity()) {
            throw this.factory.markCorrupt(StandardException.newException("XSTB0.M", var7, new Object[0]));
         }
      } finally {
         if (var2) {
            this.xact.close();
            this.xact = null;
         }

      }

   }

   RawTransaction getTransaction() {
      return this.xact;
   }

   RawStoreFactory getFactory() {
      return this.factory;
   }

   void substituteTransaction(Xact var1) {
      Xact var2 = (Xact)this.xact;
      if (var2.xc == this) {
         var2.xc = null;
      }

      this.xact = var1;
      ((Xact)this.xact).xc = this;
   }
}
