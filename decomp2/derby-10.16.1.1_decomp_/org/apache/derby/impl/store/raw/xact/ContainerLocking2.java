package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerLock;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class ContainerLocking2 extends NoLocking {
   private final LockFactory lf;

   protected ContainerLocking2() {
      this.lf = null;
   }

   protected ContainerLocking2(LockFactory var1) {
      this.lf = var1;
   }

   public boolean lockContainer(Transaction var1, ContainerHandle var2, boolean var3, boolean var4) throws StandardException {
      ContainerLock var5 = var4 ? ContainerLock.CX : ContainerLock.CS;
      Object var6 = var4 ? var1 : var2.getUniqueId();
      return this.lf.lockObject(var1.getCompatibilitySpace(), var6, var2.getId(), var5, var3 ? -2 : 0);
   }

   public void unlockContainer(Transaction var1, ContainerHandle var2) {
      if (var2.isReadOnly()) {
         this.lf.unlockGroup(var1.getCompatibilitySpace(), var2.getUniqueId());
      }

   }

   public int getMode() {
      return 2;
   }
}
