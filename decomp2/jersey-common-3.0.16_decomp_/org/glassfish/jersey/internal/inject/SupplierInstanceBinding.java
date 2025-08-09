package org.glassfish.jersey.internal.inject;

import java.util.function.Supplier;

public class SupplierInstanceBinding extends Binding {
   private final Supplier supplier;

   SupplierInstanceBinding(Supplier supplier) {
      this.supplier = supplier;
   }

   public Supplier getSupplier() {
      return this.supplier;
   }
}
