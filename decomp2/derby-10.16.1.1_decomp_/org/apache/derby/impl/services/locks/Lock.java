package org.apache.derby.impl.services.locks;

import java.util.List;
import java.util.Map;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.Latch;
import org.apache.derby.iapi.services.locks.Lockable;

class Lock implements Latch, Control {
   private final CompatibilitySpace space;
   private final Lockable ref;
   private final Object qualifier;
   int count;

   protected Lock(CompatibilitySpace var1, Lockable var2, Object var3) {
      this.space = var1;
      this.ref = var2;
      this.qualifier = var3;
   }

   public final Lockable getLockable() {
      return this.ref;
   }

   public final CompatibilitySpace getCompatabilitySpace() {
      return this.space;
   }

   public final Object getQualifier() {
      return this.qualifier;
   }

   public final int getCount() {
      return this.count;
   }

   final Lock copy() {
      return new Lock(this.space, this.ref, this.qualifier);
   }

   void grant() {
      ++this.count;
      this.ref.lockEvent(this);
   }

   int unlock(int var1) {
      if (var1 > this.count) {
         var1 = this.count;
      }

      this.count -= var1;
      if (this.count == 0) {
         this.ref.unlockEvent(this);
      }

      return var1;
   }

   public final int hashCode() {
      return this.ref.hashCode() ^ this.space.hashCode();
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof Lock var2)) {
         return false;
      } else {
         return this.space == var2.space && this.ref.equals(var2.ref) && this.qualifier == var2.qualifier;
      }
   }

   public LockControl getLockControl() {
      return new LockControl(this, this.ref);
   }

   public Lock getLock(CompatibilitySpace var1, Object var2) {
      return this.space == var1 && this.qualifier == var2 ? this : null;
   }

   public Control shallowClone() {
      return this;
   }

   public ActiveLock firstWaiter() {
      return null;
   }

   public boolean isEmpty() {
      return this.count == 0;
   }

   public boolean unlock(Latch var1, int var2) {
      if (var2 == 0) {
         var2 = var1.getCount();
      }

      this.unlock(var2);
      return false;
   }

   public void addWaiters(Map var1) {
   }

   public Lock getFirstGrant() {
      return this;
   }

   public List getGranted() {
      return null;
   }

   public List getWaiting() {
      return null;
   }

   public boolean isGrantable(boolean var1, CompatibilitySpace var2, Object var3) {
      return this.space == var2 && this.ref.lockerAlwaysCompatible() ? true : this.ref.requestCompatible(var3, this.qualifier);
   }
}
