package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class MpscArrayQueueProducerLimitField extends MpscArrayQueueMidPad {
   private static final long P_LIMIT_OFFSET = UnsafeAccess.fieldOffset(MpscArrayQueueProducerLimitField.class, "producerLimit");
   private volatile long producerLimit;

   MpscArrayQueueProducerLimitField(int capacity) {
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
