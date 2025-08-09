package org.glassfish.jersey.internal.inject;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

public class SupplierClassBinding extends Binding {
   private final Class supplierClass;
   private final Class supplierScope;

   SupplierClassBinding(Class supplierClass, Class scope) {
      this.supplierClass = supplierClass;
      this.supplierScope = scope;
   }

   public Class getSupplierClass() {
      return this.supplierClass;
   }

   public Class getSupplierScope() {
      return this.supplierScope;
   }
}
