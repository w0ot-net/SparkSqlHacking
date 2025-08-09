package org.apache.logging.log4j.layout.template.json.util;

import java.util.function.Supplier;

public class DummyRecycler implements Recycler {
   private final Supplier supplier;

   public DummyRecycler(final Supplier supplier) {
      this.supplier = supplier;
   }

   public Object acquire() {
      return this.supplier.get();
   }

   public void release(final Object value) {
   }
}
