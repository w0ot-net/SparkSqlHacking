package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerLock;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class RowLocking3Escalate extends ContainerLocking3 {
   protected RowLocking3Escalate(LockFactory var1) {
      super(var1);
   }

   public boolean lockContainer(Transaction var1, ContainerHandle var2, boolean var3, boolean var4) throws StandardException {
      var4 = false;
      if (this.lf.isLockHeld(var1.getCompatibilitySpace(), var1, var2.getId(), ContainerLock.CIX)) {
         var4 = true;
      }

      boolean var5 = super.lockContainer(var1, var2, var3, var4);
      if (!var5) {
         return false;
      } else {
         this.lf.unlockGroup(var1.getCompatibilitySpace(), var1, new EscalateContainerKey(var2.getId()));
         return true;
      }
   }
}
