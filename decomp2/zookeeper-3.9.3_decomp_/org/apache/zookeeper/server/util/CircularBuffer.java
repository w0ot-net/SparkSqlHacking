package org.apache.zookeeper.server.util;

import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicInteger;

public class CircularBuffer {
   private final Object[] buffer;
   private final int capacity;
   private int oldest;
   private AtomicInteger numberOfElements = new AtomicInteger();

   public CircularBuffer(Class clazz, int capacity) {
      if (capacity <= 0) {
         throw new IllegalArgumentException("CircularBuffer capacity should be greater than 0");
      } else {
         this.buffer = Array.newInstance(clazz, capacity);
         this.capacity = capacity;
      }
   }

   public synchronized void write(Object element) {
      int newSize = this.numberOfElements.incrementAndGet();
      if (newSize > this.capacity) {
         this.buffer[this.oldest] = element;
         this.oldest = ++this.oldest % this.capacity;
         this.numberOfElements.decrementAndGet();
      } else {
         int index = (this.oldest + this.numberOfElements.get() - 1) % this.capacity;
         this.buffer[index] = element;
      }

   }

   public synchronized Object take() {
      int newSize = this.numberOfElements.decrementAndGet();
      if (newSize < 0) {
         this.numberOfElements.incrementAndGet();
         return null;
      } else {
         T polled = (T)this.buffer[this.oldest];
         this.oldest = ++this.oldest % this.capacity;
         return polled;
      }
   }

   public synchronized Object peek() {
      return this.numberOfElements.get() <= 0 ? null : this.buffer[this.oldest];
   }

   public int size() {
      return this.numberOfElements.get();
   }

   public boolean isEmpty() {
      return this.numberOfElements.get() <= 0;
   }

   public boolean isFull() {
      return this.numberOfElements.get() >= this.capacity;
   }

   public synchronized void reset() {
      this.numberOfElements.set(0);
   }
}
