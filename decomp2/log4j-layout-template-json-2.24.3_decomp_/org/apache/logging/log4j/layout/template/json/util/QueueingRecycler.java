package org.apache.logging.log4j.layout.template.json.util;

import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class QueueingRecycler implements Recycler {
   private final Supplier supplier;
   private final Consumer cleaner;
   private final Queue queue;

   public QueueingRecycler(final Supplier supplier, final Consumer cleaner, final Queue queue) {
      this.supplier = supplier;
      this.cleaner = cleaner;
      this.queue = queue;
   }

   Queue getQueue() {
      return this.queue;
   }

   public Object acquire() {
      V value = (V)this.queue.poll();
      if (value == null) {
         return this.supplier.get();
      } else {
         this.cleaner.accept(value);
         return value;
      }
   }

   public void release(final Object value) {
      this.queue.offer(value);
   }
}
