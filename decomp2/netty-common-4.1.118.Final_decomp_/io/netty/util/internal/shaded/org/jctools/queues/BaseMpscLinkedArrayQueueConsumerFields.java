package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class BaseMpscLinkedArrayQueueConsumerFields extends BaseMpscLinkedArrayQueuePad2 {
   private static final long C_INDEX_OFFSET = UnsafeAccess.fieldOffset(BaseMpscLinkedArrayQueueConsumerFields.class, "consumerIndex");
   private volatile long consumerIndex;
   protected long consumerMask;
   protected Object[] consumerBuffer;

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
