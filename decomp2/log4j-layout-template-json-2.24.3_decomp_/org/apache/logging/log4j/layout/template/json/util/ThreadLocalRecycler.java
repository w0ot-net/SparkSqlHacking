package org.apache.logging.log4j.layout.template.json.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ThreadLocalRecycler implements Recycler {
   private final Consumer cleaner;
   private final ThreadLocal holder;

   public ThreadLocalRecycler(final Supplier supplier, final Consumer cleaner) {
      this.cleaner = cleaner;
      this.holder = ThreadLocal.withInitial(supplier);
   }

   public Object acquire() {
      V value = (V)this.holder.get();
      this.cleaner.accept(value);
      return value;
   }

   public void release(final Object value) {
   }
}
