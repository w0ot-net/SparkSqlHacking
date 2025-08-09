package org.glassfish.jersey.internal.inject;

import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.WebApplicationException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.ServiceFinder;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedProvider;

public class Injections {
   public static InjectionManager createInjectionManager() {
      return lookupInjectionManagerFactory(RuntimeType.SERVER).create();
   }

   public static InjectionManager createInjectionManager(RuntimeType type) {
      return lookupInjectionManagerFactory(type).create();
   }

   public static InjectionManager createInjectionManager(Binder binder) {
      InjectionManagerFactory injectionManagerFactory = lookupInjectionManagerFactory(RuntimeType.SERVER);
      InjectionManager injectionManager = injectionManagerFactory.create();
      injectionManager.register(binder);
      return injectionManager;
   }

   public static InjectionManager createInjectionManager(Object parent) {
      return lookupInjectionManagerFactory(RuntimeType.SERVER).create(parent);
   }

   private static InjectionManagerFactory lookupInjectionManagerFactory(RuntimeType type) {
      return (InjectionManagerFactory)lookupService(InjectionManagerFactory.class, type).orElseThrow(() -> new IllegalStateException(LocalizationMessages.INJECTION_MANAGER_FACTORY_NOT_FOUND()));
   }

   private static Optional lookupService(Class clazz, RuntimeType type) {
      List<RankedProvider<T>> providers = new LinkedList();

      for(Object provider : ServiceFinder.find(clazz)) {
         ConstrainedTo constrain = (ConstrainedTo)provider.getClass().getAnnotation(ConstrainedTo.class);
         if (constrain == null || type == constrain.value()) {
            providers.add(new RankedProvider(provider));
         }
      }

      providers.sort(new RankedComparator(RankedComparator.Order.DESCENDING));
      return providers.isEmpty() ? Optional.empty() : Optional.ofNullable(((RankedProvider)providers.get(0)).getProvider());
   }

   public static Object getOrCreate(InjectionManager injectionManager, Class clazz) {
      try {
         T component = (T)injectionManager.getInstance(clazz);
         return component == null ? injectionManager.createAndInitialize(clazz) : component;
      } catch (RuntimeException e) {
         Throwable throwable = e.getCause();
         if (throwable != null && WebApplicationException.class.isAssignableFrom(throwable.getClass())) {
            throw (WebApplicationException)throwable;
         } else {
            throw e;
         }
      }
   }
}
