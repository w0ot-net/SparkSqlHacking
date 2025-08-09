package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class BaseLinkedQueueProducerNodeRef extends BaseLinkedQueuePad0 {
   static final long P_NODE_OFFSET = UnsafeAccess.fieldOffset(BaseLinkedQueueProducerNodeRef.class, "producerNode");
   private volatile LinkedQueueNode producerNode;

   final void spProducerNode(LinkedQueueNode newValue) {
      UnsafeAccess.UNSAFE.putObject(this, P_NODE_OFFSET, newValue);
   }

   final void soProducerNode(LinkedQueueNode newValue) {
      UnsafeAccess.UNSAFE.putOrderedObject(this, P_NODE_OFFSET, newValue);
   }

   final LinkedQueueNode lvProducerNode() {
      return this.producerNode;
   }

   final boolean casProducerNode(LinkedQueueNode expect, LinkedQueueNode newValue) {
      return UnsafeAccess.UNSAFE.compareAndSwapObject(this, P_NODE_OFFSET, expect, newValue);
   }

   final LinkedQueueNode lpProducerNode() {
      return this.producerNode;
   }
}
