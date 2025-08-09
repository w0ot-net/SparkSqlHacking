package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

abstract class MpmcAtomicArrayQueueConsumerIndexField extends MpmcAtomicArrayQueueL2Pad {
   private static final AtomicLongFieldUpdater C_INDEX_UPDATER = AtomicLongFieldUpdater.newUpdater(MpmcAtomicArrayQueueConsumerIndexField.class, "consumerIndex");
   private volatile long consumerIndex;

   MpmcAtomicArrayQueueConsumerIndexField(int capacity) {
      super(capacity);
   }

   public final long lvConsumerIndex() {
      return this.consumerIndex;
   }

   final boolean casConsumerIndex(long expect, long newValue) {
      return C_INDEX_UPDATER.compareAndSet(this, expect, newValue);
   }
}
