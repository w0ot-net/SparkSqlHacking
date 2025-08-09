package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class BaseMpscLinkedArrayQueueProducerFields extends BaseMpscLinkedArrayQueuePad1 {
   private static final long P_INDEX_OFFSET = UnsafeAccess.fieldOffset(BaseMpscLinkedArrayQueueProducerFields.class, "producerIndex");
   private volatile long producerIndex;

   public final long lvProducerIndex() {
      return this.producerIndex;
   }

   final void soProducerIndex(long newValue) {
      UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, newValue);
   }

   final boolean casProducerIndex(long expect, long newValue) {
      return UnsafeAccess.UNSAFE.compareAndSwapLong(this, P_INDEX_OFFSET, expect, newValue);
   }
}
