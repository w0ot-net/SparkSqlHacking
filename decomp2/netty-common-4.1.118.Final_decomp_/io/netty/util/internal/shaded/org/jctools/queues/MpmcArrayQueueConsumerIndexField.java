package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class MpmcArrayQueueConsumerIndexField extends MpmcArrayQueueL2Pad {
   private static final long C_INDEX_OFFSET = UnsafeAccess.fieldOffset(MpmcArrayQueueConsumerIndexField.class, "consumerIndex");
   private volatile long consumerIndex;

   MpmcArrayQueueConsumerIndexField(int capacity) {
      super(capacity);
   }

   public final long lvConsumerIndex() {
      return this.consumerIndex;
   }

   final boolean casConsumerIndex(long expect, long newValue) {
      return UnsafeAccess.UNSAFE.compareAndSwapLong(this, C_INDEX_OFFSET, expect, newValue);
   }
}
