package org.apache.logging.log4j.layout.template.json.util;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class DummyRecyclerFactory implements RecyclerFactory {
   private static final DummyRecyclerFactory INSTANCE = new DummyRecyclerFactory();

   private DummyRecyclerFactory() {
   }

   public static DummyRecyclerFactory getInstance() {
      return INSTANCE;
   }

   public Recycler create(final Supplier supplier, final Consumer cleaner) {
      return new DummyRecycler(supplier);
   }
}
