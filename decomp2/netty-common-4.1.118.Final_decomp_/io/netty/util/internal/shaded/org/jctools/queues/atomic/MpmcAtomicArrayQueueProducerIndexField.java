package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

abstract class MpmcAtomicArrayQueueProducerIndexField extends MpmcAtomicArrayQueueL1Pad {
   private static final AtomicLongFieldUpdater P_INDEX_UPDATER = AtomicLongFieldUpdater.newUpdater(MpmcAtomicArrayQueueProducerIndexField.class, "producerIndex");
   private volatile long producerIndex;

   MpmcAtomicArrayQueueProducerIndexField(int capacity) {
      super(capacity);
   }

   public final long lvProducerIndex() {
      return this.producerIndex;
   }

   final boolean casProducerIndex(long expect, long newValue) {
      return P_INDEX_UPDATER.compareAndSet(this, expect, newValue);
   }
}
