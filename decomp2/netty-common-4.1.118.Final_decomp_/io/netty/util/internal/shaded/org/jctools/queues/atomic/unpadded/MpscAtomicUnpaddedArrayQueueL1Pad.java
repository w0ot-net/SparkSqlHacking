package io.netty.util.internal.shaded.org.jctools.queues.atomic.unpadded;

import io.netty.util.internal.shaded.org.jctools.queues.atomic.AtomicReferenceArrayQueue;

abstract class MpscAtomicUnpaddedArrayQueueL1Pad extends AtomicReferenceArrayQueue {
   MpscAtomicUnpaddedArrayQueueL1Pad(int capacity) {
      super(capacity);
   }
}
