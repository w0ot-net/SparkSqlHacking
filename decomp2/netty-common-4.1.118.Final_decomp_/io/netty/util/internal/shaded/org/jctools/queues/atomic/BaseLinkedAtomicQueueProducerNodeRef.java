package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

abstract class BaseLinkedAtomicQueueProducerNodeRef extends BaseLinkedAtomicQueuePad0 {
   private static final AtomicReferenceFieldUpdater P_NODE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(BaseLinkedAtomicQueueProducerNodeRef.class, LinkedQueueAtomicNode.class, "producerNode");
   private volatile LinkedQueueAtomicNode producerNode;

   final void spProducerNode(LinkedQueueAtomicNode newValue) {
      P_NODE_UPDATER.lazySet(this, newValue);
   }

   final void soProducerNode(LinkedQueueAtomicNode newValue) {
      P_NODE_UPDATER.lazySet(this, newValue);
   }

   final LinkedQueueAtomicNode lvProducerNode() {
      return this.producerNode;
   }

   final boolean casProducerNode(LinkedQueueAtomicNode expect, LinkedQueueAtomicNode newValue) {
      return P_NODE_UPDATER.compareAndSet(this, expect, newValue);
   }

   final LinkedQueueAtomicNode lpProducerNode() {
      return this.producerNode;
   }

   protected final LinkedQueueAtomicNode xchgProducerNode(LinkedQueueAtomicNode newValue) {
      return (LinkedQueueAtomicNode)P_NODE_UPDATER.getAndSet(this, newValue);
   }
}
