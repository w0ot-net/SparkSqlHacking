package scala.collection.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

abstract class MainNode extends BasicNode {
   public static final AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(MainNode.class, MainNode.class, "prev");
   public volatile MainNode prev = null;

   public abstract int cachedSize(Object var1);

   public abstract int knownSize();

   public boolean CAS_PREV(MainNode oldval, MainNode nval) {
      return updater.compareAndSet(this, oldval, nval);
   }

   public void WRITE_PREV(MainNode nval) {
      updater.set(this, nval);
   }

   /** @deprecated */
   @Deprecated
   public MainNode READ_PREV() {
      return (MainNode)updater.get(this);
   }
}
