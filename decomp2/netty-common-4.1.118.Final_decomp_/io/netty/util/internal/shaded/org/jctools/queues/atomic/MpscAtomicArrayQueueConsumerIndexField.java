package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

abstract class MpscAtomicArrayQueueConsumerIndexField extends MpscAtomicArrayQueueL2Pad {
   private static final AtomicLongFieldUpdater C_INDEX_UPDATER = AtomicLongFieldUpdater.newUpdater(MpscAtomicArrayQueueConsumerIndexField.class, "consumerIndex");
   private volatile long consumerIndex;

   MpscAtomicArrayQueueConsumerIndexField(int capacity) {
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
