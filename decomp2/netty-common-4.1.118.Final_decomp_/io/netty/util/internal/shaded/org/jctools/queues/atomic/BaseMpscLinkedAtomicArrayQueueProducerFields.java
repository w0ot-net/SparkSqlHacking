package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

abstract class BaseMpscLinkedAtomicArrayQueueProducerFields extends BaseMpscLinkedAtomicArrayQueuePad1 {
   private static final AtomicLongFieldUpdater P_INDEX_UPDATER = AtomicLongFieldUpdater.newUpdater(BaseMpscLinkedAtomicArrayQueueProducerFields.class, "producerIndex");
   private volatile long producerIndex;

   public final long lvProducerIndex() {
      return this.producerIndex;
   }

   final void soProducerIndex(long newValue) {
      P_INDEX_UPDATER.lazySet(this, newValue);
   }

   final boolean casProducerIndex(long expect, long newValue) {
      return P_INDEX_UPDATER.compareAndSet(this, expect, newValue);
   }
}
