package org.glassfish.jersey.inject.hk2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.reflection.ParameterizedTypeImpl;
import org.glassfish.jersey.internal.inject.DisposableSupplier;

public class SupplierFactoryBridge implements Factory {
   private final ServiceLocator locator;
   private final ParameterizedType beanType;
   private final String beanName;
   private final boolean disposable;
   private Map disposableSuppliers = Collections.synchronizedMap(new IdentityHashMap());

   SupplierFactoryBridge(ServiceLocator locator, Type beanType, String beanName, boolean disposable) {
      this.locator = locator;
      this.beanType = new ParameterizedTypeImpl(Supplier.class, new Type[]{beanType});
      this.beanName = beanName;
      this.disposable = disposable;
   }

   public Object provide() {
      if (this.beanType != null) {
         Supplier<T> supplier = (Supplier)this.locator.getService(this.beanType, this.beanName, new Annotation[0]);
         T instance = (T)supplier.get();
         if (this.disposable) {
            this.disposableSuppliers.put(instance, (DisposableSupplier)supplier);
         }

         return instance;
      } else {
         return null;
      }
   }

   public void dispose(Object instance) {
      if (this.disposable) {
         DisposableSupplier<T> disposableSupplier = (DisposableSupplier)this.disposableSuppliers.remove(instance);
         disposableSupplier.dispose(instance);
      }

   }
}
