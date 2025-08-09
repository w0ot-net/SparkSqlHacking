package io.netty.util.internal.shaded.org.jctools.queues.atomic;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueueUtil;
import java.util.Iterator;

abstract class BaseLinkedAtomicQueue extends BaseLinkedAtomicQueuePad2 {
   public final Iterator iterator() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      return this.getClass().getName();
   }

   protected final LinkedQueueAtomicNode newNode() {
      return new LinkedQueueAtomicNode();
   }

   protected final LinkedQueueAtomicNode newNode(Object e) {
      return new LinkedQueueAtomicNode(e);
   }

   public final int size() {
      LinkedQueueAtomicNode<E> chaserNode = this.lvConsumerNode();
      LinkedQueueAtomicNode<E> producerNode = this.lvProducerNode();

      int size;
      for(size = 0; chaserNode != producerNode && chaserNode != null && size < Integer.MAX_VALUE; ++size) {
         LinkedQueueAtomicNode<E> next = chaserNode.lvNext();
         if (next == chaserNode) {
            return size;
         }

         chaserNode = next;
      }

      return size;
   }

   public boolean isEmpty() {
      LinkedQueueAtomicNode<E> consumerNode = this.lvConsumerNode();
      LinkedQueueAtomicNode<E> producerNode = this.lvProducerNode();
      return consumerNode == producerNode;
   }

   protected Object getSingleConsumerNodeValue(LinkedQueueAtomicNode currConsumerNode, LinkedQueueAtomicNode nextNode) {
      E nextValue = (E)nextNode.getAndNullValue();
      currConsumerNode.soNext(currConsumerNode);
      this.spConsumerNode(nextNode);
      return nextValue;
   }

   public Object poll() {
      LinkedQueueAtomicNode<E> currConsumerNode = this.lpConsumerNode();
      LinkedQueueAtomicNode<E> nextNode = currConsumerNode.lvNext();
      if (nextNode != null) {
         return this.getSingleConsumerNodeValue(currConsumerNode, nextNode);
      } else if (currConsumerNode != this.lvProducerNode()) {
         nextNode = this.spinWaitForNextNode(currConsumerNode);
         return this.getSingleConsumerNodeValue(currConsumerNode, nextNode);
      } else {
         return null;
      }
   }

   public Object peek() {
      LinkedQueueAtomicNode<E> currConsumerNode = this.lpConsumerNode();
      LinkedQueueAtomicNode<E> nextNode = currConsumerNode.lvNext();
      if (nextNode != null) {
         return nextNode.lpValue();
      } else if (currConsumerNode != this.lvProducerNode()) {
         nextNode = this.spinWaitForNextNode(currConsumerNode);
         return nextNode.lpValue();
      } else {
         return null;
      }
   }

   LinkedQueueAtomicNode spinWaitForNextNode(LinkedQueueAtomicNode currNode) {
      LinkedQueueAtomicNode<E> nextNode;
      while((nextNode = currNode.lvNext()) == null) {
      }

      return nextNode;
   }

   public Object relaxedPoll() {
      LinkedQueueAtomicNode<E> currConsumerNode = this.lpConsumerNode();
      LinkedQueueAtomicNode<E> nextNode = currConsumerNode.lvNext();
      return nextNode != null ? this.getSingleConsumerNodeValue(currConsumerNode, nextNode) : null;
   }

   public Object relaxedPeek() {
      LinkedQueueAtomicNode<E> nextNode = this.lpConsumerNode().lvNext();
      return nextNode != null ? nextNode.lpValue() : null;
   }

   public boolean relaxedOffer(Object e) {
      return this.offer(e);
   }

   public int drain(MessagePassingQueue.Consumer c, int limit) {
      if (null == c) {
         throw new IllegalArgumentException("c is null");
      } else if (limit < 0) {
         throw new IllegalArgumentException("limit is negative: " + limit);
      } else if (limit == 0) {
         return 0;
      } else {
         LinkedQueueAtomicNode<E> chaserNode = this.lpConsumerNode();

         for(int i = 0; i < limit; ++i) {
            LinkedQueueAtomicNode<E> nextNode = chaserNode.lvNext();
            if (nextNode == null) {
               return i;
            }

            E nextValue = (E)this.getSingleConsumerNodeValue(chaserNode, nextNode);
            chaserNode = nextNode;
            c.accept(nextValue);
         }

         return limit;
      }
   }

   public int drain(MessagePassingQueue.Consumer c) {
      return MessagePassingQueueUtil.drain(this, c);
   }

   public void drain(MessagePassingQueue.Consumer c, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
      MessagePassingQueueUtil.drain(this, c, wait, exit);
   }

   public int capacity() {
      return -1;
   }
}
