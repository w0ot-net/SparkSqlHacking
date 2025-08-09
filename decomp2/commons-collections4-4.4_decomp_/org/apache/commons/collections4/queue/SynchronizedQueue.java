package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.collection.SynchronizedCollection;

public class SynchronizedQueue extends SynchronizedCollection implements Queue {
   private static final long serialVersionUID = 1L;

   public static SynchronizedQueue synchronizedQueue(Queue queue) {
      return new SynchronizedQueue(queue);
   }

   protected SynchronizedQueue(Queue queue) {
      super(queue);
   }

   protected SynchronizedQueue(Queue queue, Object lock) {
      super(queue, lock);
   }

   protected Queue decorated() {
      return (Queue)super.decorated();
   }

   public Object element() {
      synchronized(this.lock) {
         return this.decorated().element();
      }
   }

   public boolean equals(Object object) {
      if (object == this) {
         return true;
      } else {
         synchronized(this.lock) {
            return this.decorated().equals(object);
         }
      }
   }

   public int hashCode() {
      synchronized(this.lock) {
         return this.decorated().hashCode();
      }
   }

   public boolean offer(Object e) {
      synchronized(this.lock) {
         return this.decorated().offer(e);
      }
   }

   public Object peek() {
      synchronized(this.lock) {
         return this.decorated().peek();
      }
   }

   public Object poll() {
      synchronized(this.lock) {
         return this.decorated().poll();
      }
   }

   public Object remove() {
      synchronized(this.lock) {
         return this.decorated().remove();
      }
   }
}
