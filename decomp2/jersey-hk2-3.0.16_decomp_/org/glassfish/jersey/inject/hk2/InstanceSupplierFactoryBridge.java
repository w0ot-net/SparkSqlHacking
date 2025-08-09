package org.glassfish.jersey.inject.hk2;

import java.util.function.Supplier;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.internal.inject.DisposableSupplier;

public class InstanceSupplierFactoryBridge implements Factory {
   private Supplier supplier;
   private boolean disposable;

   InstanceSupplierFactoryBridge(Supplier supplier, boolean disposable) {
      this.supplier = supplier;
      this.disposable = disposable;
   }

   public Object provide() {
      return this.supplier.get();
   }

   public void dispose(Object instance) {
      if (this.disposable) {
         ((DisposableSupplier)this.supplier).dispose(instance);
      }

   }
}
