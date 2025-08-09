package org.apache.logging.log4j.layout.template.json.util;

import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class QueueingRecyclerFactory implements RecyclerFactory {
   private final Supplier queueSupplier;

   public QueueingRecyclerFactory(final Supplier queueSupplier) {
      this.queueSupplier = queueSupplier;
   }

   public Recycler create(final Supplier supplier, final Consumer cleaner) {
      Queue<V> queue = (Queue)this.queueSupplier.get();
      return new QueueingRecycler(supplier, cleaner, queue);
   }
}
