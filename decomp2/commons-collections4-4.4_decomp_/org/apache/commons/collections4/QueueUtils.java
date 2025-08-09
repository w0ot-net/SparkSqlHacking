package org.apache.commons.collections4;

import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.queue.SynchronizedQueue;
import org.apache.commons.collections4.queue.TransformedQueue;
import org.apache.commons.collections4.queue.UnmodifiableQueue;

public class QueueUtils {
   public static final Queue EMPTY_QUEUE = UnmodifiableQueue.unmodifiableQueue(new LinkedList());

   private QueueUtils() {
   }

   public static Queue synchronizedQueue(Queue queue) {
      return SynchronizedQueue.synchronizedQueue(queue);
   }

   public static Queue unmodifiableQueue(Queue queue) {
      return UnmodifiableQueue.unmodifiableQueue(queue);
   }

   public static Queue predicatedQueue(Queue queue, Predicate predicate) {
      return PredicatedQueue.predicatedQueue(queue, predicate);
   }

   public static Queue transformingQueue(Queue queue, Transformer transformer) {
      return TransformedQueue.transformingQueue(queue, transformer);
   }

   public static Queue emptyQueue() {
      return EMPTY_QUEUE;
   }
}
