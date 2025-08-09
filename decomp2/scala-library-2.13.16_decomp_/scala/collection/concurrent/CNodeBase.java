package scala.collection.concurrent;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

abstract class CNodeBase extends MainNode {
   public static final AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(CNodeBase.class, "csize");
   public volatile int csize = -1;

   public boolean CAS_SIZE(int oldval, int nval) {
      return updater.compareAndSet(this, oldval, nval);
   }

   public void WRITE_SIZE(int nval) {
      updater.set(this, nval);
   }

   public int READ_SIZE() {
      return updater.get(this);
   }
}
