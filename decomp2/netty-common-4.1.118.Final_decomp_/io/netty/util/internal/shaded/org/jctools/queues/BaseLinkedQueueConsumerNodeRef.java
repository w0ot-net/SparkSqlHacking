package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;

abstract class BaseLinkedQueueConsumerNodeRef extends BaseLinkedQueuePad1 {
   private static final long C_NODE_OFFSET = UnsafeAccess.fieldOffset(BaseLinkedQueueConsumerNodeRef.class, "consumerNode");
   private LinkedQueueNode consumerNode;

   final void spConsumerNode(LinkedQueueNode newValue) {
      this.consumerNode = newValue;
   }

   final LinkedQueueNode lvConsumerNode() {
      return (LinkedQueueNode)UnsafeAccess.UNSAFE.getObjectVolatile(this, C_NODE_OFFSET);
   }

   final LinkedQueueNode lpConsumerNode() {
      return this.consumerNode;
   }
}
