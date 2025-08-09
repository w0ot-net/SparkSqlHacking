package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;

abstract class BaseMpscLinkedAtomicArrayQueueColdProducerFields extends BaseMpscLinkedAtomicArrayQueuePad3 {
   private static final AtomicLongFieldUpdater P_LIMIT_UPDATER = AtomicLongFieldUpdater.newUpdater(BaseMpscLinkedAtomicArrayQueueColdProducerFields.class, "producerLimit");
   private volatile long producerLimit;
   protected long producerMask;
   protected AtomicReferenceArray producerBuffer;

   final long lvProducerLimit() {
      return this.producerLimit;
   }

   final boolean casProducerLimit(long expect, long newValue) {
      return P_LIMIT_UPDATER.compareAndSet(this, expect, newValue);
   }

   final void soProducerLimit(long newValue) {
      P_LIMIT_UPDATER.lazySet(this, newValue);
   }
}
