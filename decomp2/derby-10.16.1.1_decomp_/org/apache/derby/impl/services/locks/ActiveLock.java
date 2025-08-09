package org.apache.derby.impl.services.locks;

import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Lockable;
import org.apache.derby.shared.common.error.StandardException;

public final class ActiveLock extends Lock {
   byte wakeUpNow;
   boolean potentiallyGranted;
   protected boolean canSkip;

   protected ActiveLock(CompatibilitySpace var1, Lockable var2, Object var3) {
      super(var1, var2, var3);
   }

   protected boolean setPotentiallyGranted() {
      if (!this.potentiallyGranted) {
         this.potentiallyGranted = true;
         return true;
      } else {
         return false;
      }
   }

   protected void clearPotentiallyGranted() {
      this.potentiallyGranted = false;
   }

   protected synchronized byte waitForGrant(int var1) throws StandardException {
      if (this.wakeUpNow == 0) {
         try {
            if (var1 == -1) {
               this.wait();
            } else if (var1 > 0) {
               this.wait((long)var1);
            }
         } catch (InterruptedException var3) {
            this.wakeUpNow = 3;
         }
      }

      byte var2 = this.wakeUpNow;
      this.wakeUpNow = 0;
      return var2;
   }

   protected synchronized void wakeUp(byte var1) {
      if (this.wakeUpNow != 2) {
         this.wakeUpNow = var1;
      }

      this.notify();
   }
}
