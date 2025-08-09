package io.netty.util.internal.shaded.org.jctools.queues.unpadded;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class MpscUnpaddedArrayQueueConsumerIndexField extends MpscUnpaddedArrayQueueL2Pad {
   private static final long C_INDEX_OFFSET = UnsafeAccess.fieldOffset(MpscUnpaddedArrayQueueConsumerIndexField.class, "consumerIndex");
   private volatile long consumerIndex;

   MpscUnpaddedArrayQueueConsumerIndexField(int capacity) {
      super(capacity);
   }

   public final long lvConsumerIndex() {
      return this.consumerIndex;
   }

   final long lpConsumerIndex() {
      return UnsafeAccess.UNSAFE.getLong(this, C_INDEX_OFFSET);
   }

   final void soConsumerIndex(long newValue) {
      UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, newValue);
   }
}
