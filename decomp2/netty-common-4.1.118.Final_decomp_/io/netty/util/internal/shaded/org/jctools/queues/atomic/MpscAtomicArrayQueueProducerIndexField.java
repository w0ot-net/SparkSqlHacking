package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

abstract class MpscAtomicArrayQueueProducerIndexField extends MpscAtomicArrayQueueL1Pad {
   private static final AtomicLongFieldUpdater P_INDEX_UPDATER = AtomicLongFieldUpdater.newUpdater(MpscAtomicArrayQueueProducerIndexField.class, "producerIndex");
   private volatile long producerIndex;

   MpscAtomicArrayQueueProducerIndexField(int capacity) {
      super(capacity);
   }

   public final long lvProducerIndex() {
      return this.producerIndex;
   }

   final boolean casProducerIndex(long expect, long newValue) {
      return P_INDEX_UPDATER.compareAndSet(this, expect, newValue);
   }
}
