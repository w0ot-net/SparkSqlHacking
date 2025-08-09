package io.netty.handler.codec.http2;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

final class MaxCapacityQueue implements Queue {
   private final Queue queue;
   private final int maxCapacity;

   MaxCapacityQueue(Queue queue, int maxCapacity) {
      this.queue = queue;
      this.maxCapacity = maxCapacity;
   }

   public boolean add(Object element) {
      if (this.offer(element)) {
         return true;
      } else {
         throw new IllegalStateException();
      }
   }

   public boolean offer(Object element) {
      return this.maxCapacity <= this.queue.size() ? false : this.queue.offer(element);
   }

   public Object remove() {
      return this.queue.remove();
   }

   public Object poll() {
      return this.queue.poll();
   }

   public Object element() {
      return this.queue.element();
   }

   public Object peek() {
      return this.queue.peek();
   }

   public int size() {
      return this.queue.size();
   }

   public boolean isEmpty() {
      return this.queue.isEmpty();
   }

   public boolean contains(Object o) {
      return this.queue.contains(o);
   }

   public Iterator iterator() {
      return this.queue.iterator();
   }

   public Object[] toArray() {
      return this.queue.toArray();
   }

   public Object[] toArray(Object[] a) {
      return this.queue.toArray(a);
   }

   public boolean remove(Object o) {
      return this.queue.remove(o);
   }

   public boolean containsAll(Collection c) {
      return this.queue.containsAll(c);
   }

   public boolean addAll(Collection c) {
      if (this.maxCapacity >= this.size() + c.size()) {
         return this.queue.addAll(c);
      } else {
         throw new IllegalStateException();
      }
   }

   public boolean removeAll(Collection c) {
      return this.queue.removeAll(c);
   }

   public boolean retainAll(Collection c) {
      return this.queue.retainAll(c);
   }

   public void clear() {
      this.queue.clear();
   }
}
