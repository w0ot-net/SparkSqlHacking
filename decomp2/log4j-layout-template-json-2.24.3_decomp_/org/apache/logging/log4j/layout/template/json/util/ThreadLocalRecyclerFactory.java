package org.apache.logging.log4j.layout.template.json.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class ThreadLocalRecyclerFactory implements RecyclerFactory {
   private static final ThreadLocalRecyclerFactory INSTANCE = new ThreadLocalRecyclerFactory();

   private ThreadLocalRecyclerFactory() {
   }

   public static ThreadLocalRecyclerFactory getInstance() {
      return INSTANCE;
   }

   public Recycler create(final Supplier supplier, final Consumer cleaner) {
      return new ThreadLocalRecycler(supplier, cleaner);
   }
}
