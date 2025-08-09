package io.netty.util.internal.shaded.org.jctools.queues;

import java.util.Iterator;

abstract class BaseLinkedQueue extends BaseLinkedQueuePad2 {
   public final Iterator iterator() {
      throw new UnsupportedOperationException();
   }

   public String toString() {
      return this.getClass().getName();
   }

   protected final LinkedQueueNode newNode() {
      return new LinkedQueueNode();
   }

   protected final LinkedQueueNode newNode(Object e) {
      return new LinkedQueueNode(e);
   }

   public final int size() {
      LinkedQueueNode<E> chaserNode = this.lvConsumerNode();
      LinkedQueueNode<E> producerNode = this.lvProducerNode();

      int size;
      for(size = 0; chaserNode != producerNode && chaserNode != null && size < Integer.MAX_VALUE; ++size) {
         LinkedQueueNode<E> next = chaserNode.lvNext();
         if (next == chaserNode) {
            return size;
         }

         chaserNode = next;
      }

      return size;
   }

   public boolean isEmpty() {
      LinkedQueueNode<E> consumerNode = this.lvConsumerNode();
      LinkedQueueNode<E> producerNode = this.lvProducerNode();
      return consumerNode == producerNode;
   }

   protected Object getSingleConsumerNodeValue(LinkedQueueNode currConsumerNode, LinkedQueueNode nextNode) {
      E nextValue = (E)nextNode.getAndNullValue();
      currConsumerNode.soNext(currConsumerNode);
      this.spConsumerNode(nextNode);
      return nextValue;
   }

   public Object poll() {
      LinkedQueueNode<E> currConsumerNode = this.lpConsumerNode();
      LinkedQueueNode<E> nextNode = currConsumerNode.lvNext();
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
      LinkedQueueNode<E> currConsumerNode = this.lpConsumerNode();
      LinkedQueueNode<E> nextNode = currConsumerNode.lvNext();
      if (nextNode != null) {
         return nextNode.lpValue();
      } else if (currConsumerNode != this.lvProducerNode()) {
         nextNode = this.spinWaitForNextNode(currConsumerNode);
         return nextNode.lpValue();
      } else {
         return null;
      }
   }

   LinkedQueueNode spinWaitForNextNode(LinkedQueueNode currNode) {
      LinkedQueueNode<E> nextNode;
      while((nextNode = currNode.lvNext()) == null) {
      }

      return nextNode;
   }

   public Object relaxedPoll() {
      LinkedQueueNode<E> currConsumerNode = this.lpConsumerNode();
      LinkedQueueNode<E> nextNode = currConsumerNode.lvNext();
      return nextNode != null ? this.getSingleConsumerNodeValue(currConsumerNode, nextNode) : null;
   }

   public Object relaxedPeek() {
      LinkedQueueNode<E> nextNode = this.lpConsumerNode().lvNext();
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
         LinkedQueueNode<E> chaserNode = this.lpConsumerNode();

         for(int i = 0; i < limit; ++i) {
            LinkedQueueNode<E> nextNode = chaserNode.lvNext();
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
