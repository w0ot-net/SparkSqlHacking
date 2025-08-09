package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class MpmcArrayQueueProducerIndexField extends MpmcArrayQueueL1Pad {
   private static final long P_INDEX_OFFSET = UnsafeAccess.fieldOffset(MpmcArrayQueueProducerIndexField.class, "producerIndex");
   private volatile long producerIndex;

   MpmcArrayQueueProducerIndexField(int capacity) {
      super(capacity);
   }

   public final long lvProducerIndex() {
      return this.producerIndex;
   }

   final boolean casProducerIndex(long expect, long newValue) {
      return UnsafeAccess.UNSAFE.compareAndSwapLong(this, P_INDEX_OFFSET, expect, newValue);
   }
}
