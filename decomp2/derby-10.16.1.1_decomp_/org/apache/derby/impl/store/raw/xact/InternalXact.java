package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.store.raw.data.DataFactory;
import org.apache.derby.iapi.store.raw.log.LogFactory;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.shared.common.error.StandardException;

public class InternalXact extends Xact {
   protected InternalXact(XactFactory var1, LogFactory var2, DataFactory var3, DataValueFactory var4) {
      super(var1, (Xact)null, var2, var3, var4, false, (CompatibilitySpace)null, false);
      this.setPostComplete();
   }

   public int setSavePoint(String var1, Object var2) throws StandardException {
      throw StandardException.newException("40XT7", new Object[0]);
   }

   public void checkLogicalOperationOk() throws StandardException {
      throw StandardException.newException("40XT7", new Object[0]);
   }

   public boolean recoveryRollbackFirst() {
      return true;
   }

   protected void doComplete(Integer var1) throws StandardException {
      if (var1.equals(ABORT)) {
         super.doComplete(var1);
      }

   }

   protected void setIdleState() {
      super.setIdleState();
      if (this.countObservers() != 0) {
         try {
            super.setActiveState();
         } catch (StandardException var2) {
         }
      }

   }
}
