package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

abstract class BaseLinkedAtomicQueueConsumerNodeRef extends BaseLinkedAtomicQueuePad1 {
   private static final AtomicReferenceFieldUpdater C_NODE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(BaseLinkedAtomicQueueConsumerNodeRef.class, LinkedQueueAtomicNode.class, "consumerNode");
   private volatile LinkedQueueAtomicNode consumerNode;

   final void spConsumerNode(LinkedQueueAtomicNode newValue) {
      C_NODE_UPDATER.lazySet(this, newValue);
   }

   final LinkedQueueAtomicNode lvConsumerNode() {
      return this.consumerNode;
   }

   final LinkedQueueAtomicNode lpConsumerNode() {
      return this.consumerNode;
   }
}
