package io.netty.util.internal.shaded.org.jctools.queues.atomic.unpadded;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

abstract class MpscAtomicUnpaddedArrayQueueConsumerIndexField extends MpscAtomicUnpaddedArrayQueueL2Pad {
   private static final AtomicLongFieldUpdater C_INDEX_UPDATER = AtomicLongFieldUpdater.newUpdater(MpscAtomicUnpaddedArrayQueueConsumerIndexField.class, "consumerIndex");
   private volatile long consumerIndex;

   MpscAtomicUnpaddedArrayQueueConsumerIndexField(int capacity) {
      super(capacity);
   }

   public final long lvConsumerIndex() {
      return this.consumerIndex;
   }

   final long lpConsumerIndex() {
      return this.consumerIndex;
   }

   final void soConsumerIndex(long newValue) {
      C_INDEX_UPDATER.lazySet(this, newValue);
   }
}
