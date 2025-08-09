package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerLock;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.shared.common.error.StandardException;

public class ContainerLocking3 extends NoLocking {
   protected final LockFactory lf;

   protected ContainerLocking3(LockFactory var1) {
      this.lf = var1;
   }

   public boolean lockContainer(Transaction var1, ContainerHandle var2, boolean var3, boolean var4) throws StandardException {
      ContainerLock var5 = var4 ? ContainerLock.CX : ContainerLock.CS;
      return this.lf.lockObject(var1.getCompatibilitySpace(), var1, var2.getId(), var5, var3 ? -2 : 0);
   }

   public int getMode() {
      return 2;
   }
}
