package org.apache.derby.impl.services.reflect;

import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.ShExLockable;
import org.apache.derby.iapi.services.locks.ShExQual;

class ClassLoaderLock extends ShExLockable {
   private UpdateLoader myLoader;

   ClassLoaderLock(UpdateLoader var1) {
      this.myLoader = var1;
   }

   public void unlockEvent(Latch var1) {
      super.unlockEvent(var1);
      if (var1.getQualifier().equals(ShExQual.EX)) {
         this.myLoader.needReload();
      }

   }
}
