package org.glassfish.jersey.internal.inject;

import jakarta.inject.Provider;
import jakarta.ws.rs.core.GenericType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.glassfish.jersey.internal.LocalizationMessages;

public abstract class AbstractBinder implements Binder {
   private List internalBindings = new ArrayList();
   private List installed = new ArrayList();
   private InjectionManager injectionManager;
   private boolean configured = false;

   protected abstract void configure();

   void setInjectionManager(InjectionManager injectionManager) {
      this.injectionManager = injectionManager;
   }

   protected final Provider createManagedInstanceProvider(Class clazz) {
      return () -> {
         if (this.injectionManager == null) {
            throw new IllegalStateException(LocalizationMessages.INJECTION_MANAGER_NOT_PROVIDED());
         } else {
            return this.injectionManager.getInstance(clazz);
         }
      };
   }

   public ClassBinding bind(Class serviceType) {
      ClassBinding<T> binding = Bindings.service(serviceType);
      this.internalBindings.add(binding);
      return binding;
   }

   public Binding bind(Binding binding) {
      this.internalBindings.add(binding);
      return binding;
   }

   public ClassBinding bindAsContract(Class serviceType) {
      ClassBinding<T> binding = Bindings.serviceAsContract(serviceType);
      this.internalBindings.add(binding);
      return binding;
   }

   public ClassBinding bindAsContract(GenericType serviceType) {
      ClassBinding<T> binding = Bindings.service(serviceType);
      this.internalBindings.add(binding);
      return binding;
   }

   public ClassBinding bindAsContract(Type serviceType) {
      ClassBinding<Object> binding = Bindings.serviceAsContract(serviceType);
      this.internalBindings.add(binding);
      return binding;
   }

   public InstanceBinding bind(Object service) {
      InstanceBinding<T> binding = Bindings.service(service);
      this.internalBindings.add(binding);
      return binding;
   }

   public SupplierClassBinding bindFactory(Class supplierType, Class supplierScope) {
      SupplierClassBinding<T> binding = Bindings.supplier(supplierType, supplierScope);
      this.internalBindings.add(binding);
      return binding;
   }

   public SupplierClassBinding bindFactory(Class supplierType) {
      SupplierClassBinding<T> binding = Bindings.supplier(supplierType);
      this.internalBindings.add(binding);
      return binding;
   }

   public SupplierInstanceBinding bindFactory(Supplier factory) {
      SupplierInstanceBinding<T> binding = Bindings.supplier(factory);
      this.internalBindings.add(binding);
      return binding;
   }

   public InjectionResolverBinding bind(InjectionResolver resolver) {
      InjectionResolverBinding<T> binding = Bindings.injectionResolver(resolver);
      this.internalBindings.add(binding);
      return binding;
   }

   public final void install(AbstractBinder... binders) {
      Stream var10000 = Arrays.stream(binders).filter(Objects::nonNull);
      List var10001 = this.installed;
      var10000.forEach(var10001::add);
   }

   public Collection getBindings() {
      this.invokeConfigure();
      List<Binding> bindings = (List)this.installed.stream().flatMap((binder) -> Bindings.getBindings(this.injectionManager, binder).stream()).collect(Collectors.toList());
      bindings.addAll(this.internalBindings);
      return bindings;
   }

   private void invokeConfigure() {
      if (!this.configured) {
         this.configure();
         this.configured = true;
      }

   }
}
