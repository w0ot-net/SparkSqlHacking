package org.apache.derby.impl.store.access;

import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.ShExLockable;

class CacheLock extends ShExLockable {
   private PropertyConglomerate pc;

   CacheLock(PropertyConglomerate var1) {
      this.pc = var1;
   }

   public void unlockEvent(Latch var1) {
      super.unlockEvent(var1);
      this.pc.resetCache();
   }
}
