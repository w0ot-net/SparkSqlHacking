package org.glassfish.jersey.internal.inject;

import jakarta.ws.rs.core.GenericType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.function.Supplier;
import org.glassfish.jersey.internal.util.ReflectionHelper;

public final class Bindings {
   private Bindings() {
      throw new AssertionError("Utility class instantiation forbidden.");
   }

   public static Collection getBindings(InjectionManager injectionManager, Binder binder) {
      if (binder instanceof AbstractBinder) {
         ((AbstractBinder)binder).setInjectionManager(injectionManager);
      }

      return binder.getBindings();
   }

   public static ClassBinding service(Class serviceType) {
      return new ClassBinding(serviceType);
   }

   public static ClassBinding serviceAsContract(Class serviceType) {
      return (ClassBinding)(new ClassBinding(serviceType)).to(serviceType);
   }

   public static ClassBinding service(GenericType serviceType) {
      return (ClassBinding)(new ClassBinding(serviceType.getRawType())).asType((Class)serviceType.getType());
   }

   public static ClassBinding serviceAsContract(GenericType serviceType) {
      return (ClassBinding)((ClassBinding)(new ClassBinding(serviceType.getRawType())).asType((Class)serviceType.getType())).to(serviceType.getType());
   }

   public static ClassBinding serviceAsContract(Type serviceType) {
      return (ClassBinding)((ClassBinding)(new ClassBinding(ReflectionHelper.getRawClass(serviceType))).asType((Class)serviceType)).to(serviceType);
   }

   public static InstanceBinding service(Object service) {
      return new InstanceBinding(service);
   }

   public static InstanceBinding serviceAsContract(Object service) {
      return new InstanceBinding(service, service.getClass());
   }

   public static SupplierClassBinding supplier(Class supplierType, Class supplierScope) {
      return new SupplierClassBinding(supplierType, supplierScope);
   }

   public static SupplierClassBinding supplier(Class supplierType) {
      return new SupplierClassBinding(supplierType, (Class)null);
   }

   public static SupplierInstanceBinding supplier(Supplier supplier) {
      return new SupplierInstanceBinding(supplier);
   }

   public static InjectionResolverBinding injectionResolver(InjectionResolver resolver) {
      return new InjectionResolverBinding(resolver);
   }
}
