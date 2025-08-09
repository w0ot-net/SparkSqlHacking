package io.netty.util.internal.shaded.org.jctools.queues.unpadded;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class MpscUnpaddedArrayQueueProducerLimitField extends MpscUnpaddedArrayQueueMidPad {
   private static final long P_LIMIT_OFFSET = UnsafeAccess.fieldOffset(MpscUnpaddedArrayQueueProducerLimitField.class, "producerLimit");
   private volatile long producerLimit;

   MpscUnpaddedArrayQueueProducerLimitField(int capacity) {
      super(capacity);
      this.producerLimit = (long)capacity;
   }

   final long lvProducerLimit() {
      return this.producerLimit;
   }

   final void soProducerLimit(long newValue) {
      UnsafeAccess.UNSAFE.putOrderedLong(this, P_LIMIT_OFFSET, newValue);
   }
}
